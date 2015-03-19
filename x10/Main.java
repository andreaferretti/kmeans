
@x10.runtime.impl.java.X10Generated
public class Main extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    public static final x10.rtt.RuntimeType<Main> $RTT = 
        x10.rtt.NamedType.<Main> make("Main",
                                      Main.class);
    
    public x10.rtt.RuntimeType<?> $getRTT() { return $RTT; }
    
    public x10.rtt.Type<?> $getParam(int i) { return null; }
    
    private Object writeReplace() throws java.io.ObjectStreamException {
        return new x10.serialization.SerializationProxy(this);
    }
    
    public static x10.serialization.X10JavaSerializable $_deserialize_body(Main $_obj, x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + Main.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
        Main $_obj = new Main((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
        
    }
    
    // constructor just for allocation
    public Main(final java.lang.System[] $dummy) {
        
    }
    
    

    
    //#line 6 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
    final public static long times = 10L;
    
    
    //#line 8 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
    public static class $Main extends x10.runtime.impl.java.Runtime
    {
        // java main method
        public static void main(java.lang.String[] args) {
            // start native runtime
            new $Main().start(args);
        }
        
        // called by native runtime inside main x10 thread
        public void runtimeCallback(final x10.core.Rail<java.lang.String> args) {
            // call the original app-main method
            Main.main(args);
        }
    }
    
    // the original app-main method
    public static void main(final x10.core.Rail<java.lang.String> id$1287373) {
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final x10.regionarray.Array xs = ((x10.regionarray.Array)(new x10.regionarray.Array<RichPoint>((java.lang.System[]) null, RichPoint.$RTT).x10$regionarray$Array$$init$S(((long)(100000L)))));
        
        //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        double[] java_xs = new double[100000];
        int java_xs_counter = 0;
        double[] java_ys = new double[100000];
        int java_ys_counter = 0;
        try {
        com.fasterxml.jackson.core.JsonFactory factory = new com.fasterxml.jackson.core.JsonFactory();
        com.fasterxml.jackson.core.JsonParser jp = factory.createJsonParser(new java.io.File("/home/andrea/workspace/kmeans/points.json"));
        int i = 0;
        while (true) {
        com.fasterxml.jackson.core.JsonToken actual = jp.nextValue();
        while (actual == com.fasterxml.jackson.core.JsonToken.START_ARRAY) {
        actual = jp.nextValue();
        }
        try {
        double x = jp.getDoubleValue();
        jp.nextToken();
        double y = jp.getDoubleValue();
        java_xs[i] = x;
        java_ys[i] = y;
        i++;
        } catch(Exception ex) {
        //ex.printStackTrace();
        break;
        }
        actual = jp.nextToken();
        while (jp.nextToken() == com.fasterxml.jackson.core.JsonToken.END_ARRAY) {
        actual = jp.nextToken();
        }
        }
        jp.close();
        } catch (Exception err) {
        err.printStackTrace();
        };
        
        //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final long i$1291259min$1291327 = 0L;
        
        //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final long i$1291259max$1291328 = 99999L;
        
        //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        long i$1291318 = i$1291259min$1291327;
        
        //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        for (;
             true;
             ) {
            
            //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final long t$1291319 = i$1291318;
            
            //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final boolean t$1291320 = ((t$1291319) <= (((long)(i$1291259max$1291328))));
            
            //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            if (!(t$1291320)) {
                
                //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
                break;
            }
            
            //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final long i$1291315 = i$1291318;
            
            //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final double t$1291312 = java_xs[java_xs_counter++];
            
            //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final double t$1291313 = java_ys[java_ys_counter++];
            
            //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final RichPoint t$1291314 = new RichPoint((java.lang.System[]) null).RichPoint$$init$S(t$1291312, t$1291313);
            
            //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            ((x10.regionarray.Array<RichPoint>)xs).$set__1x10$regionarray$Array$$T$G((long)(i$1291315), ((RichPoint)(t$1291314)));
            
            //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final long t$1291316 = i$1291318;
            
            //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final long t$1291317 = ((t$1291316) + (((long)(1L))));
            
            //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            i$1291318 = t$1291317;
        }
        
        //#line 16 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final long beforeTime = x10.lang.System.currentTimeMillis$O();
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final long i$1291277min$1291329 = 1L;
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final long i$1291277max$1291330 = Main.times;
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        long i$1291324 = i$1291277min$1291329;
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        for (;
             true;
             ) {
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final long t$1291325 = i$1291324;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final boolean t$1291326 = ((t$1291325) <= (((long)(i$1291277max$1291330))));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            if (!(t$1291326)) {
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
                break;
            }
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final long i$1291321 = i$1291324;
            
            //#line 19 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            KMeans.run__0$1RichPoint$2(((x10.regionarray.Array)(xs)));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final long t$1291322 = i$1291324;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            final long t$1291323 = ((t$1291322) + (((long)(1L))));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            i$1291324 = t$1291323;
        }
        
        //#line 22 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final long afterTime = x10.lang.System.currentTimeMillis$O();
        
        //#line 23 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final x10.io.Printer t$1291310 = ((x10.io.Printer)(x10.io.Console.get$OUT()));
        
        //#line 23 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final long t$1291307 = ((afterTime) - (((long)(beforeTime))));
        
        //#line 23 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final long t$1291308 = Main.times;
        
        //#line 23 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final long t$1291309 = ((t$1291307) / (((long)(t$1291308))));
        
        //#line 23 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        final java.lang.String t$1291311 = (("Elapsed time is ") + ((x10.core.Long.$box(t$1291309))));
        
        //#line 23 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        t$1291310.println(((java.lang.Object)(t$1291311)));
    }
    
    
    //#line 4 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
    final public Main Main$$this$Main() {
        
        //#line 4 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
        return Main.this;
    }
    
    
    //#line 4 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
    // creation method for java code (1-phase java constructor)
    public Main() {
        this((java.lang.System[]) null);
        Main$$init$S();
    }
    
    // constructor for non-virtual call
    final public Main Main$$init$S() {
         {
            
            //#line 4 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            
            
            //#line 4 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
            this.__fieldInitializers_Main();
        }
        return this;
    }
    
    
    
    //#line 4 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/Main.x10"
    final public void __fieldInitializers_Main() {
        
    }
    
    public static long get$times() {
        return Main.times;
    }
}


