package ccut.common;

 public class BaseThreadLocal
 {
   private static final ThreadLocal<Integer> treadLocal = new ThreadLocal<>();

   public static Integer getCurrentId() {
     return treadLocal.get();
   }

   public static void setCurrentId(Integer id) {
     treadLocal.set(id);
   }
   public static void remove() {
     treadLocal.remove();
   }
 }

