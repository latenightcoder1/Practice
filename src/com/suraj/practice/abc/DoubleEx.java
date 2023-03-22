package com.suraj.practice.abc;

public class DoubleEx {

  public static void main(String[] args) {
   /* Double d=1.0;
    if(d instanceof Number){
      d=d++;
    }
    boolean con1=(d==2);
    if(con1){
      System.out.println("P A1");
    }
    double e=1.0;
    if((Double) e instanceof Double | d++==e++){
      d+=d;
    }
    boolean con2=(d>2);
    if(con2){
      System.out.println("d2");
    }
  }*/
    /*System.out.println(List.of(0,1,2,3).stream().skip(4).toArray().length);*/
  /* String chars=" a b a ";
   String newChars=chars.strip().repeat(4);
   String spl[] = newChars.split("[ab]");
   int blank=0;
   for(String t:spl){
     if(t.isBlank()){
       blank++;
     }
   }
   System.out.println(blank);*/
    int i=2;int j=0;
    j=(i++ + i++)*--i;
    System.out.println(j);

  }
}
