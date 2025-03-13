package org.example;
import java.io.*;
import java.util.*;

import static java.lang.Math.abs;


public class Task1 {
    private static final double G=6.67e-11;
    private static final double MASAPAMANT=5.972e24;
    private int nrEngines;
    private double accEngine;


    private List<Integer> diametruUtil=new ArrayList<Integer>();
    private List<Double> masaUtil=new ArrayList<Double>();

    private List<Integer> period = new ArrayList<Integer>();
    private List<Double> radius = new ArrayList<Double>();
    private static double AU1 = 149597870.7;
    public int getPeriod(int v)
    {
        return period.get(v);
    }
    public void citire1()
    {

        try(BufferedReader br = new BufferedReader(new FileReader("E:\\Taskuri\\Planetary_Data.txt"))){
            String line;
            while((line=br.readLine())!=null)
            {

                String[] parts=line.split(",");
                String diametru=parts[0].split(" = ")[1].split(" ")[0];
                String masa=parts[1].split(" = ")[1].split(" ")[0];

                diametruUtil.add(Integer.parseInt(diametru));
                masaUtil.add(Double.parseDouble(masa));
            }

        }catch(IOException e){
            System.out.println("Erroare de citire din fisier");
            e.printStackTrace();
        }
    }
    public void citire2()
    {
        try(BufferedReader br = new BufferedReader(new FileReader("E:\\Taskuri\\Rocket_Data.txt"))){
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Number of rocket engines:")) {

                    nrEngines = Integer.parseInt(line.split(": ")[1].trim());

                } else if (line.startsWith("Acceleration per engine:")) {

                    accEngine = Double.parseDouble(line.split(": ")[1].split(" ")[0].trim());

                }
            }
            System.out.println("Number of Engines: "+nrEngines+" Acceleration per Engine: "+accEngine);

        }catch(IOException e){
            System.out.println("Erroare de citire din fisier");
            e.printStackTrace();
        }
    }
    public void citire3()
    {
        try(BufferedReader br = new BufferedReader(new FileReader("E:\\Taskuri\\Solar_System_Data.txt"))){
            String line;
            while((line=br.readLine())!=null)
            {

                String[] parts=line.split(",");
                String periodS=parts[0].split(" = ")[1].split(" ")[0];
                String radiusS=parts[1].split(" = ")[1].split(" ")[0];

                period.add(Integer.parseInt(periodS));
                radius.add(Double.parseDouble(radiusS));
            }

        }catch(IOException e){
            System.out.println("Erroare de citire din fisier");
            e.printStackTrace();
        }
    }
    public int Conversie(int diametru)
    {
        return (diametru/2)*1000;
    }
    /**Task1*/
    public double[] calculateV(){
        double[]V=new double[diametruUtil.size()];
        for(int i=0;i<diametruUtil.size();i++)
        {
            double r = (diametruUtil.get(i)/2)*1000;
            double M = masaUtil.get(i)*MASAPAMANT;
            double Ve=Math.sqrt((2*M*G)/r);
            V[i]=Ve;
        }
        return V;
    }
    public String afisare1() {
        StringBuilder sb = new StringBuilder();
        double[] g = calculateV();
        for (int i = 0; i < diametruUtil.size(); i++) {
            sb.append("Diametru: ").append(diametruUtil.get(i))
                    .append(" | Masa: ").append(masaUtil.get(i))
                    .append(" | Velocity: ").append(g[i]).append("\n");
        }
        return sb.toString();
    }
    /**Task2*/
    public double calculateT(){
        double t=11183.0;
        t=t/(nrEngines*accEngine);
        return t;
    }
    public double[] distanceTask2(){
        double[] V=new double[diametruUtil.size()];
        double[] D=new double[diametruUtil.size()];
        V=calculateV();
        System.out.println();
        for(int i=0;i<diametruUtil.size();i++)
        {
            D[i]=(V[i]*calculateT())+((nrEngines*accEngine)*(calculateT()*calculateT()))/2;
        }
        return D;
    }
    public String afisare2()
    {
        double[] d=distanceTask2();
        StringBuilder sb2 = new StringBuilder();
        for(int i=0;i<diametruUtil.size();i++)
        {
            sb2.append("Diametru: ").append(diametruUtil.get(i))
                    .append(" | Distanta: ").append(d[i])
                    .append("\n");
        }
        return sb2.toString();
    }

    /**Task3*/
    public double[] distanceTask3(){
        double[] distanceCenter=new double[radius.size()];
        double[] escapeDistance = distanceTask2();
        for(int i=0;i<radius.size();i++)
        {
            distanceCenter[i]=(radius.get(i)*AU1)+escapeDistance[i];
        }
        return distanceCenter;
    }
    public String afisare3()
    {
        double[] distanceCenter = distanceTask3();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < radius.size(); i++) {
            sb.append("Radius: ").append(radius.get(i))
                    .append(" | DistanceCenter: ").append(distanceCenter[i])
                    .append("\n");
        }
        return sb.toString();
    }
    /**Task4*/
    public double angular(List<Integer> period,int WhatPlanet,int days){
        if(WhatPlanet>8 || WhatPlanet<0)
            throw new IllegalArgumentException("Planeta gresita");

        double Total = period.get(WhatPlanet);
        double angularVelocity=360.0/Total;
        double angle=((days%Total)*angularVelocity)%360;
        return angle;
    }
    public List<Integer> getPeriod() {
        return period;
    }

    /**Task5*/
    public double CalculateDistance(int planet1Index,int planet2Index,int days)
    {
        double r1=radius.get(planet1Index);
        double r2=radius.get(planet2Index);
        double angR1=angular(period,planet1Index,days);
        double angR2=angular(period,planet2Index,days);

        double angleDif=Math.toRadians(angR1-angR2);

        double distance = Math.sqrt(Math.pow(r1,2)+Math.pow(r2,2)-(2*r1*r2*Math.cos(angleDif)));

        return distance;
    }
    public boolean isCollision(int planet1Index,int planet2Index,int days)
    {
        double distance = CalculateDistance(planet1Index,planet2Index,days);

        for(int i=0;i<radius.size();i++)
        {
            if(i!=planet1Index && i!=planet2Index)
            {
                double planetDistance=radius.get(i);
                if(planetDistance>Math.min(radius.get(planet1Index),radius.get(planet2Index)) && planetDistance < Math.max(radius.get(planet1Index),radius.get(planet2Index)))
                {
                    return true;
                }
            }
        }
        return false;
    }
    public double[] findOptimalTransferWindow(int planet1Index,int planet2Index){
        int wait=10*365;
        double[] trasnferWindow=new double[2];

        double minDistance=Double.MAX_VALUE;
        int optimalDay=-1;

        for(int days=0;days<=wait;days++)
        {
            if(!isCollision(planet1Index,planet2Index,days))
            {
                double distance = CalculateDistance(planet1Index,planet2Index,days);
                if(distance<minDistance)
                {
                    minDistance=distance;
                    optimalDay=days;
                }
            }
        }
        if(optimalDay==-1)
            return null;
        trasnferWindow[0]=minDistance;
        trasnferWindow[1]=optimalDay;
        return trasnferWindow;
    }

}
