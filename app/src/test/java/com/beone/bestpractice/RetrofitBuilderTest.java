package com.beone.bestpractice;

import org.junit.Test;

@SuppressWarnings("ALL")
public class RetrofitBuilderTest {


  @Test
  public void test1(){
    long starttime = System.nanoTime();
    System.out.println("Test Start");
    System.out.println("Start From : "+starttime);
    for (int i = 0; i < 20; i++) {
//      System.out.println("K"+i+" "+RetrofitBuilder.getInstance().hashCode()+" API "+ RetrofitBuilder.getApiService().hashCode());
    }
    System.out.println("Finish on : "+System.nanoTime()+" Long Process "+(System.nanoTime()-starttime));
    System.out.println("Test END");
  }
  @Test
  public void test2(){
    long starttime = System.nanoTime();
    System.out.println("Test Start");
    System.out.println("Start From : "+starttime);
    for (int i = 0; i < 20; i++) {
//      System.out.println("K"+i+" "+RetrofitBuilder.getInstance().hashCode()+" API "+ RetrofitBuilder.getApiService().hashCode());
    }
    System.out.println("Finish on : "+System.nanoTime()+" Long Process "+(System.nanoTime()-starttime));
    System.out.println("Test END");
  }
}