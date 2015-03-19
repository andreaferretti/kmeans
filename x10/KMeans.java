
@x10.runtime.impl.java.X10Generated
public class KMeans extends x10.core.Ref implements x10.serialization.X10JavaSerializable
{
    public static final x10.rtt.RuntimeType<KMeans> $RTT = 
        x10.rtt.NamedType.<KMeans> make("KMeans",
                                        KMeans.class);
    
    public x10.rtt.RuntimeType<?> $getRTT() { return $RTT; }
    
    public x10.rtt.Type<?> $getParam(int i) { return null; }
    
    private Object writeReplace() throws java.io.ObjectStreamException {
        return new x10.serialization.SerializationProxy(this);
    }
    
    public static x10.serialization.X10JavaSerializable $_deserialize_body(KMeans $_obj, x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + KMeans.class + " calling"); } 
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
        KMeans $_obj = new KMeans((java.lang.System[]) null);
        $deserializer.record_reference($_obj);
        return $_deserialize_body($_obj, $deserializer);
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
        
    }
    
    // constructor just for allocation
    public KMeans(final java.lang.System[] $dummy) {
        
    }
    
    

    
    
    //#line 8 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    public static double sq$O(final double x) {
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final double t$1291848 = ((x) * (((double)(x))));
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        return t$1291848;
    }
    
    
    //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    public static double dist$O(final RichPoint p1, final RichPoint p2) {
        
        //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final RichPoint t$1291849 = p1.$minus(((RichPoint)(p2)));
        
        //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final double t$1291850 = t$1291849.modulus$O();
        
        //#line 12 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        return t$1291850;
    }
    
    
    //#line 14 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    public static RichPoint average__0$1RichPoint$2(final x10.util.ArrayList<RichPoint> xs) {
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.core.Rail t$1291854 = ((x10.core.Rail<RichPoint>)
                                          ((x10.util.ArrayList<RichPoint>)xs).toRail());
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.core.fun.Fun_0_2 t$1291855 = ((x10.core.fun.Fun_0_2)(new KMeans.$Closure$16()));
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final double t$1291852 = ((double)(long)(((long)(0L))));
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final double t$1291853 = ((double)(long)(((long)(0L))));
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final RichPoint t$1291856 = new RichPoint((java.lang.System[]) null).RichPoint$$init$S(t$1291852, t$1291853);
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final RichPoint t$1291858 = x10.util.RailUtils.<RichPoint, RichPoint> reduce__0$1x10$util$RailUtils$$T$2__1$1x10$util$RailUtils$$U$3x10$util$RailUtils$$T$3x10$util$RailUtils$$U$2__2x10$util$RailUtils$$U$G(RichPoint.$RTT, RichPoint.$RTT, ((x10.core.Rail)(t$1291854)), ((x10.core.fun.Fun_0_2)(t$1291855)), ((RichPoint)(t$1291856)));
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final long t$1291857 = ((x10.util.ArrayList<RichPoint>)xs).size$O();
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final double t$1291859 = ((double)(long)(((long)(t$1291857))));
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final RichPoint t$1291860 = t$1291858.$over((double)(t$1291859));
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        return t$1291860;
    }
    
    
    //#line 17 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    public static RichPoint closest__1$1RichPoint$2(final RichPoint p, final x10.util.ArrayList<RichPoint> choices) {
        
        //#line 30 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final long t$1291861 = ((x10.util.ArrayList<RichPoint>)choices).size$O();
        
        //#line 30 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.core.Rail dists = ((x10.core.Rail)(new x10.core.Rail<KMeans.WPoint$483>(KMeans.WPoint$483.$RTT, t$1291861)));
        
        //#line 32 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.core.fun.Fun_0_1 pointDist = ((x10.core.fun.Fun_0_1)(new KMeans.$Closure$17(p)));
        
        //#line 33 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.core.Rail t$1291864 = ((x10.core.Rail<RichPoint>)
                                          ((x10.util.ArrayList<RichPoint>)choices).toRail());
        
        //#line 33 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        x10.util.RailUtils.<RichPoint, KMeans.WPoint$483> map__0$1x10$util$RailUtils$$T$2__1$1x10$util$RailUtils$$U$2__2$1x10$util$RailUtils$$T$3x10$util$RailUtils$$U$2(RichPoint.$RTT, KMeans.WPoint$483.$RTT, ((x10.core.Rail)(t$1291864)), ((x10.core.Rail)(dists)), ((x10.core.fun.Fun_0_1)(pointDist)));
        
        //#line 35 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.core.fun.Fun_0_2 reduceTask = ((x10.core.fun.Fun_0_2)(new KMeans.$Closure$18()));
        
        //#line 44 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final double t$1291872 = ((double)(long)(((long)(0L))));
        
        //#line 44 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final double t$1291873 = ((double)(long)(((long)(0L))));
        
        //#line 44 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final RichPoint t$1291874 = new RichPoint((java.lang.System[]) null).RichPoint$$init$S(t$1291872, t$1291873);
        
        //#line 44 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final KMeans.WPoint$483 unity = new KMeans.WPoint$483((java.lang.System[]) null).KMeans$WPoint$483$$init$S(t$1291874, ((double)(0.0)), ((boolean)(false)));
        
        //#line 46 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final KMeans.WPoint$483 t$1291875 = x10.util.RailUtils.<KMeans.WPoint$483, KMeans.WPoint$483> reduce__0$1x10$util$RailUtils$$T$2__1$1x10$util$RailUtils$$U$3x10$util$RailUtils$$T$3x10$util$RailUtils$$U$2__2x10$util$RailUtils$$U$G(KMeans.WPoint$483.$RTT, KMeans.WPoint$483.$RTT, ((x10.core.Rail)(dists)), ((x10.core.fun.Fun_0_2)(reduceTask)), ((KMeans.WPoint$483)(unity)));
        
        //#line 46 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final RichPoint t$1291876 = ((RichPoint)(t$1291875.p));
        
        //#line 46 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        return t$1291876;
    }
    
    
    //#line 49 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    public static x10.core.Rail clusters__0$1RichPoint$2__1$1RichPoint$2(final x10.regionarray.Array<RichPoint> xs, final x10.util.ArrayList<RichPoint> centroids) {
        
        //#line 51 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final long t$1291877 = ((x10.util.ArrayList<RichPoint>)centroids).size$O();
        
        //#line 51 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.util.HashMap hm = ((x10.util.HashMap)(new x10.util.HashMap<RichPoint, x10.util.ArrayList<RichPoint>>((java.lang.System[]) null, RichPoint.$RTT, x10.rtt.ParameterizedType.make(x10.util.ArrayList.$RTT, RichPoint.$RTT)).x10$util$HashMap$$init$S(t$1291877)));
        
        //#line 52 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.regionarray.Region t$1291981 = ((x10.regionarray.Region)(((x10.regionarray.Array<RichPoint>)xs).region));
        
        //#line 52 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.lang.Iterator p$1291982 = t$1291981.iterator();
        
        //#line 52 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        for (;
             true;
             ) {
            
            //#line 52 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291983 = ((x10.lang.Iterator<x10.lang.Point>)p$1291982).hasNext$O();
            
            //#line 52 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (!(t$1291983)) {
                
                //#line 52 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                break;
            }
            
            //#line 52 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final x10.lang.Point p$1291975 = ((x10.lang.Point)(((x10.lang.Iterator<x10.lang.Point>)p$1291982).next$G()));
            
            //#line 53 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint t$1291976 = ((x10.regionarray.Array<RichPoint>)xs).$apply$G(((x10.lang.Point)(p$1291975)));
            
            //#line 53 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint key$1291977 = KMeans.closest__1$1RichPoint$2(((RichPoint)(t$1291976)), ((x10.util.ArrayList)(centroids)));
            
            //#line 54 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final x10.util.ArrayList t$1291978 = ((x10.util.ArrayList)(new x10.util.ArrayList<RichPoint>((java.lang.System[]) null, RichPoint.$RTT).x10$util$ArrayList$$init$S()));
            
            //#line 54 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final x10.util.ArrayList value$1291979 = ((x10.util.HashMap<RichPoint, x10.util.ArrayList<RichPoint>>)hm).getOrElse__0x10$util$HashMap$$K__1x10$util$HashMap$$V$G(((RichPoint)(key$1291977)), ((x10.util.ArrayList)(t$1291978)));
            
            //#line 55 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint t$1291980 = ((x10.regionarray.Array<RichPoint>)xs).$apply$G(((x10.lang.Point)(p$1291975)));
            
            //#line 55 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            ((x10.util.ArrayList<RichPoint>)value$1291979).add__0x10$util$ArrayList$$T$O(((RichPoint)(t$1291980)));
            
            //#line 56 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            ((x10.util.HashMap<RichPoint, x10.util.ArrayList<RichPoint>>)hm).put__0x10$util$HashMap$$K__1x10$util$HashMap$$V$G(((RichPoint)(key$1291977)), ((x10.util.ArrayList)(value$1291979)));
        }
        
        //#line 59 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final long t$1291884 = ((x10.util.ArrayList<RichPoint>)centroids).size$O();
        
        //#line 59 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.core.Rail dest = ((x10.core.Rail)(new x10.core.Rail<x10.util.ArrayList<RichPoint>>(x10.rtt.ParameterizedType.make(x10.util.ArrayList.$RTT, RichPoint.$RTT), t$1291884)));
        
        //#line 60 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.core.Rail t$1291886 = ((x10.core.Rail<RichPoint>)
                                          ((x10.util.ArrayList<RichPoint>)centroids).toRail());
        
        //#line 60 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.core.fun.Fun_0_1 t$1291887 = ((x10.core.fun.Fun_0_1)(new KMeans.$Closure$19(hm, (KMeans.$Closure$19.__0$1RichPoint$3x10$util$ArrayList$1RichPoint$2$2) null)));
        
        //#line 60 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        x10.util.RailUtils.<RichPoint, x10.util.ArrayList<RichPoint>> map__0$1x10$util$RailUtils$$T$2__1$1x10$util$RailUtils$$U$2__2$1x10$util$RailUtils$$T$3x10$util$RailUtils$$U$2(RichPoint.$RTT, x10.rtt.ParameterizedType.make(x10.util.ArrayList.$RTT, RichPoint.$RTT), ((x10.core.Rail)(t$1291886)), ((x10.core.Rail)(dest)), ((x10.core.fun.Fun_0_1)(t$1291887)));
        
        //#line 61 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        return dest;
    }
    
    
    //#line 64 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    final public static long n = 10L;
    
    //#line 65 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    final public static long iters = 15L;
    
    
    //#line 67 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    public static x10.core.Rail run__0$1RichPoint$2(final x10.regionarray.Array<RichPoint> xs) {
        
        //#line 68 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final long t$1291888 = KMeans.n;
        
        //#line 68 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.util.ArrayList centroids = ((x10.util.ArrayList)(new x10.util.ArrayList<RichPoint>((java.lang.System[]) null, RichPoint.$RTT).x10$util$ArrayList$$init$S(((long)(t$1291888)))));
        
        //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final long i$1291795min$1292016 = 0L;
        
        //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final long t$1292017 = KMeans.n;
        
        //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final long i$1291795max$1292018 = 9L;
        
        //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        long i$1291994 = i$1291795min$1292016;
        
        //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        for (;
             true;
             ) {
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long t$1291995 = i$1291994;
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291996 = ((t$1291995) <= (((long)(i$1291795max$1292018))));
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (!(t$1291996)) {
                
                //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                break;
            }
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long i$1291991 = i$1291994;
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final x10.regionarray.Array t$1291984 = xs;
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long i$1291985 = i$1291991;
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long t$1291986 = ((x10.regionarray.Array<RichPoint>)t$1291984).rank;
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291987 = ((long) t$1291986) == ((long) 1L);
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291988 = !(t$1291987);
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (t$1291988) {
                
                //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final x10.lang.FailedDynamicCheckException t$1291989 = ((x10.lang.FailedDynamicCheckException)(new x10.lang.FailedDynamicCheckException(((java.lang.String)("!(t$1291776.rank == 1L)")))));
                
                //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                throw t$1291989;
            }
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint t$1291990 = ((x10.regionarray.Array<RichPoint>)t$1291984).$apply$G((long)(i$1291985));
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            ((x10.util.ArrayList<RichPoint>)centroids).$set__1x10$util$ArrayList$$T$G((long)(i$1291991), ((RichPoint)(t$1291990)));
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long t$1291992 = i$1291994;
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long t$1291993 = ((t$1291992) + (((long)(1L))));
            
            //#line 70 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            i$1291994 = t$1291993;
        }
        
        //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final long i$1291831min$1292019 = 1L;
        
        //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final long i$1291831max$1292020 = KMeans.iters;
        
        //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        long i$1292013 = i$1291831min$1292019;
        
        //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        for (;
             true;
             ) {
            
            //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long t$1292014 = i$1292013;
            
            //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1292015 = ((t$1292014) <= (((long)(i$1291831max$1292020))));
            
            //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (!(t$1292015)) {
                
                //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                break;
            }
            
            //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long k$1292010 = i$1292013;
            
            //#line 73 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final x10.core.Rail clus$1292008 = KMeans.clusters__0$1RichPoint$2__1$1RichPoint$2(((x10.regionarray.Array)(xs)), ((x10.util.ArrayList)(centroids)));
            
            //#line 74 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final x10.lang.Iterator clusIter$1292009 = ((x10.lang.Iterator<x10.util.ArrayList<RichPoint>>)
                                                         ((x10.core.Rail<x10.util.ArrayList<RichPoint>>)clus$1292008).iterator());
            
            //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long i$1291813min$1292005 = 0L;
            
            //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long t$1292006 = KMeans.n;
            
            //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long i$1291813max$1292007 = 9L;
            
            //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            long i$1292002 = i$1291813min$1292005;
            
            //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            for (;
                 true;
                 ) {
                
                //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final long t$1292003 = i$1292002;
                
                //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final boolean t$1292004 = ((t$1292003) <= (((long)(i$1291813max$1292007))));
                
                //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                if (!(t$1292004)) {
                    
                    //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                    break;
                }
                
                //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final long i$1291999 = i$1292002;
                
                //#line 76 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final x10.util.ArrayList t$1291997 = ((x10.lang.Iterator<x10.util.ArrayList<RichPoint>>)clusIter$1292009).next$G();
                
                //#line 76 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final RichPoint t$1291998 = KMeans.average__0$1RichPoint$2(((x10.util.ArrayList)(t$1291997)));
                
                //#line 76 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                ((x10.util.ArrayList<RichPoint>)centroids).$set__1x10$util$ArrayList$$T$G((long)(i$1291999), ((RichPoint)(t$1291998)));
                
                //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final long t$1292000 = i$1292002;
                
                //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final long t$1292001 = ((t$1292000) + (((long)(1L))));
                
                //#line 75 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                i$1292002 = t$1292001;
            }
            
            //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long t$1292011 = i$1292013;
            
            //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final long t$1292012 = ((t$1292011) + (((long)(1L))));
            
            //#line 72 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            i$1292013 = t$1292012;
        }
        
        //#line 85 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final x10.core.Rail t$1291913 = KMeans.clusters__0$1RichPoint$2__1$1RichPoint$2(((x10.regionarray.Array)(xs)), ((x10.util.ArrayList)(centroids)));
        
        //#line 85 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        return t$1291913;
    }
    
    
    //#line 6 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    final public KMeans KMeans$$this$KMeans() {
        
        //#line 6 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        return KMeans.this;
    }
    
    
    //#line 6 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    // creation method for java code (1-phase java constructor)
    public KMeans() {
        this((java.lang.System[]) null);
        KMeans$$init$S();
    }
    
    // constructor for non-virtual call
    final public KMeans KMeans$$init$S() {
         {
            
            //#line 6 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            
            
            //#line 6 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            this.__fieldInitializers_KMeans();
        }
        return this;
    }
    
    
    
    //#line 6 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    final public void __fieldInitializers_KMeans() {
        
    }
    
    
    //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
    @x10.runtime.impl.java.X10Generated
    public static class WPoint$483 extends x10.core.Struct implements x10.serialization.X10JavaSerializable
    {
        public static final x10.rtt.RuntimeType<WPoint$483> $RTT = 
            x10.rtt.NamedStructType.<WPoint$483> make("KMeans.WPoint$483",
                                                      WPoint$483.class,
                                                      new x10.rtt.Type[] {
                                                          x10.rtt.Types.STRUCT
                                                      });
        
        public x10.rtt.RuntimeType<?> $getRTT() { return $RTT; }
        
        public x10.rtt.Type<?> $getParam(int i) { return null; }
        
        private Object writeReplace() throws java.io.ObjectStreamException {
            return new x10.serialization.SerializationProxy(this);
        }
        
        public static x10.serialization.X10JavaSerializable $_deserialize_body(KMeans.WPoint$483 $_obj, x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + WPoint$483.class + " calling"); } 
            $_obj.dist = $deserializer.readDouble();
            $_obj.p = $deserializer.readObject();
            $_obj.valid = $deserializer.readBoolean();
            return $_obj;
        }
        
        public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            KMeans.WPoint$483 $_obj = new KMeans.WPoint$483((java.lang.System[]) null);
            return $_deserialize_body($_obj, $deserializer);
        }
        
        public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            $serializer.write(this.dist);
            $serializer.write(this.p);
            $serializer.write(this.valid);
            
        }
        
        // zero value constructor
        public WPoint$483(final java.lang.System $dummy) { this.p = new RichPoint($dummy); this.dist = 0.0; this.valid = false; }
        
        // constructor just for allocation
        public WPoint$483(final java.lang.System[] $dummy) {
            
        }
        
        
    
        
        //#line 19 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        public RichPoint p;
        
        //#line 20 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        public double dist;
        
        //#line 21 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        public boolean valid;
        
        
        //#line 23 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        // creation method for java code (1-phase java constructor)
        public WPoint$483(final RichPoint p, final double dist, final boolean valid) {
            this((java.lang.System[]) null);
            KMeans$WPoint$483$$init$S(p, dist, valid);
        }
        
        // constructor for non-virtual call
        final public KMeans.WPoint$483 KMeans$WPoint$483$$init$S(final RichPoint p, final double dist, final boolean valid) {
             {
                
                //#line 23 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                this.__fieldInitializers_WPoint();
                
                //#line 24 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                this.p = ((RichPoint)(p));
                
                //#line 25 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                this.dist = dist;
                
                //#line 26 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                this.valid = valid;
            }
            return this;
        }
        
        
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final public java.lang.String typeName() {
            try {
                return x10.rtt.Types.typeName(this);
            }
            catch (java.lang.Throwable exc$1292314) {
                throw x10.runtime.impl.java.ThrowableUtils.ensureX10Exception(exc$1292314);
            }
            
        }
        
        
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final public java.lang.String toString() {
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final java.lang.String t$1291914 = "struct WPoint: p=";
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint t$1291915 = ((RichPoint)(this.p));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final java.lang.String t$1291916 = ((t$1291914) + (t$1291915));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final java.lang.String t$1291917 = ((t$1291916) + (" dist="));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final double t$1291918 = this.dist;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final java.lang.String t$1291919 = ((t$1291917) + ((x10.core.Double.$box(t$1291918))));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final java.lang.String t$1291920 = ((t$1291919) + (" valid="));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291921 = this.valid;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final java.lang.String t$1291922 = ((t$1291920) + ((x10.core.Boolean.$box(t$1291921))));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            return t$1291922;
        }
        
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final public int hashCode() {
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            int result = 1;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291923 = result;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291925 = ((8191) * (((int)(t$1291923))));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint t$1291924 = ((RichPoint)(this.p));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291926 = t$1291924.hashCode();
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291927 = ((t$1291925) + (((int)(t$1291926))));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            result = t$1291927;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291928 = result;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291930 = ((8191) * (((int)(t$1291928))));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final double t$1291929 = this.dist;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291931 = x10.rtt.Types.hashCode(t$1291929);
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291932 = ((t$1291930) + (((int)(t$1291931))));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            result = t$1291932;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291933 = result;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291935 = ((8191) * (((int)(t$1291933))));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291934 = this.valid;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291936 = x10.rtt.Types.hashCode(t$1291934);
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291937 = ((t$1291935) + (((int)(t$1291936))));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            result = t$1291937;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final int t$1291938 = result;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            return t$1291938;
        }
        
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final public boolean equals(java.lang.Object other) {
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final java.lang.Object t$1291939 = ((java.lang.Object)(other));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291940 = KMeans.WPoint$483.$RTT.isInstance(t$1291939);
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291941 = !(t$1291940);
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (t$1291941) {
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                return false;
            }
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final java.lang.Object t$1291942 = ((java.lang.Object)(other));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final KMeans.WPoint$483 t$1291943 = ((KMeans.WPoint$483)x10.rtt.Types.asStruct(KMeans.WPoint$483.$RTT,t$1291942));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291944 = this.equals$O(((KMeans.WPoint$483)(t$1291943)));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            return t$1291944;
        }
        
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final public boolean equals$O(KMeans.WPoint$483 other) {
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint t$1291946 = ((RichPoint)(this.p));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final KMeans.WPoint$483 t$1291945 = ((KMeans.WPoint$483)(other));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint t$1291947 = ((RichPoint)(t$1291945.p));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            boolean t$1291951 = x10.rtt.Equality.equalsequals((t$1291946),(t$1291947));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (t$1291951) {
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final double t$1291949 = this.dist;
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final KMeans.WPoint$483 t$1291948 = ((KMeans.WPoint$483)(other));
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final double t$1291950 = t$1291948.dist;
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                t$1291951 = ((double) t$1291949) == ((double) t$1291950);
            }
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            boolean t$1291955 = t$1291951;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (t$1291955) {
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final boolean t$1291953 = this.valid;
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final KMeans.WPoint$483 t$1291952 = ((KMeans.WPoint$483)(other));
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final boolean t$1291954 = t$1291952.valid;
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                t$1291955 = ((boolean) t$1291953) == ((boolean) t$1291954);
            }
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291956 = t$1291955;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            return t$1291956;
        }
        
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final public boolean _struct_equals$O(java.lang.Object other) {
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final java.lang.Object t$1291957 = ((java.lang.Object)(other));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291958 = KMeans.WPoint$483.$RTT.isInstance(t$1291957);
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291959 = !(t$1291958);
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (t$1291959) {
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                return false;
            }
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final java.lang.Object t$1291960 = ((java.lang.Object)(other));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final KMeans.WPoint$483 t$1291961 = ((KMeans.WPoint$483)x10.rtt.Types.asStruct(KMeans.WPoint$483.$RTT,t$1291960));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291962 = this._struct_equals$O(((KMeans.WPoint$483)(t$1291961)));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            return t$1291962;
        }
        
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final public boolean _struct_equals$O(KMeans.WPoint$483 other) {
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint t$1291964 = ((RichPoint)(this.p));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final KMeans.WPoint$483 t$1291963 = ((KMeans.WPoint$483)(other));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint t$1291965 = ((RichPoint)(t$1291963.p));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            boolean t$1291969 = x10.rtt.Equality.equalsequals((t$1291964),(t$1291965));
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (t$1291969) {
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final double t$1291967 = this.dist;
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final KMeans.WPoint$483 t$1291966 = ((KMeans.WPoint$483)(other));
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final double t$1291968 = t$1291966.dist;
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                t$1291969 = ((double) t$1291967) == ((double) t$1291968);
            }
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            boolean t$1291973 = t$1291969;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (t$1291973) {
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final boolean t$1291971 = this.valid;
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final KMeans.WPoint$483 t$1291970 = ((KMeans.WPoint$483)(other));
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final boolean t$1291972 = t$1291970.valid;
                
                //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                t$1291973 = ((boolean) t$1291971) == ((boolean) t$1291972);
            }
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291974 = t$1291973;
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            return t$1291974;
        }
        
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final public KMeans.WPoint$483 WPoint$$this$WPoint() {
            
            //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            return KMeans.WPoint$483.this;
        }
        
        
        //#line 18 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
        final public void __fieldInitializers_WPoint() {
            
        }
    }
    
    
    public static long get$n() {
        return KMeans.n;
    }
    
    public static long get$iters() {
        return KMeans.iters;
    }
    
    @x10.runtime.impl.java.X10Generated
    final public static class $Closure$16 extends x10.core.Ref implements x10.core.fun.Fun_0_2, x10.serialization.X10JavaSerializable
    {
        public static final x10.rtt.RuntimeType<$Closure$16> $RTT = 
            x10.rtt.StaticFunType.<$Closure$16> make($Closure$16.class,
                                                     new x10.rtt.Type[] {
                                                         x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_2.$RTT, RichPoint.$RTT, RichPoint.$RTT, RichPoint.$RTT)
                                                     });
        
        public x10.rtt.RuntimeType<?> $getRTT() { return $RTT; }
        
        public x10.rtt.Type<?> $getParam(int i) { return null; }
        
        private Object writeReplace() throws java.io.ObjectStreamException {
            return new x10.serialization.SerializationProxy(this);
        }
        
        public static x10.serialization.X10JavaSerializable $_deserialize_body(KMeans.$Closure$16 $_obj, x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$16.class + " calling"); } 
            return $_obj;
        }
        
        public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            KMeans.$Closure$16 $_obj = new KMeans.$Closure$16((java.lang.System[]) null);
            $deserializer.record_reference($_obj);
            return $_deserialize_body($_obj, $deserializer);
        }
        
        public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
        }
        
        // constructor just for allocation
        public $Closure$16(final java.lang.System[] $dummy) {
            
        }
        
        // dispatcher for method abstract public (Z1,Z2)=>U.operator()(a1:Z1, a2:Z2):U
        public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return $apply((RichPoint)a1, (RichPoint)a2);
            
        }
        
        
    
        
        public RichPoint $apply(final RichPoint p1, final RichPoint p2) {
            
            //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final RichPoint t$1291851 = p1.$plus(((RichPoint)(p2)));
            
            //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            return t$1291851;
        }
        
        public $Closure$16() {
             {
                
            }
        }
        
    }
    
    @x10.runtime.impl.java.X10Generated
    final public static class $Closure$17 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
    {
        public static final x10.rtt.RuntimeType<$Closure$17> $RTT = 
            x10.rtt.StaticFunType.<$Closure$17> make($Closure$17.class,
                                                     new x10.rtt.Type[] {
                                                         x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, RichPoint.$RTT, KMeans.WPoint$483.$RTT)
                                                     });
        
        public x10.rtt.RuntimeType<?> $getRTT() { return $RTT; }
        
        public x10.rtt.Type<?> $getParam(int i) { return null; }
        
        private Object writeReplace() throws java.io.ObjectStreamException {
            return new x10.serialization.SerializationProxy(this);
        }
        
        public static x10.serialization.X10JavaSerializable $_deserialize_body(KMeans.$Closure$17 $_obj, x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$17.class + " calling"); } 
            $_obj.p = $deserializer.readObject();
            return $_obj;
        }
        
        public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            KMeans.$Closure$17 $_obj = new KMeans.$Closure$17((java.lang.System[]) null);
            $deserializer.record_reference($_obj);
            return $_deserialize_body($_obj, $deserializer);
        }
        
        public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            $serializer.write(this.p);
            
        }
        
        // constructor just for allocation
        public $Closure$17(final java.lang.System[] $dummy) {
            
        }
        
        // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
        public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply((RichPoint)a1);
            
        }
        
        
    
        
        public KMeans.WPoint$483 $apply(final RichPoint x) {
            
            //#line 32 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final double t$1291862 = KMeans.dist$O(((RichPoint)(x)), ((RichPoint)(this.p)));
            
            //#line 32 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final KMeans.WPoint$483 t$1291863 = new KMeans.WPoint$483((java.lang.System[]) null).KMeans$WPoint$483$$init$S(((RichPoint)(x)), t$1291862, ((boolean)(true)));
            
            //#line 32 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            return t$1291863;
        }
        
        public RichPoint p;
        
        public $Closure$17(final RichPoint p) {
             {
                this.p = ((RichPoint)(p));
            }
        }
        
    }
    
    @x10.runtime.impl.java.X10Generated
    final public static class $Closure$18 extends x10.core.Ref implements x10.core.fun.Fun_0_2, x10.serialization.X10JavaSerializable
    {
        public static final x10.rtt.RuntimeType<$Closure$18> $RTT = 
            x10.rtt.StaticFunType.<$Closure$18> make($Closure$18.class,
                                                     new x10.rtt.Type[] {
                                                         x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_2.$RTT, KMeans.WPoint$483.$RTT, KMeans.WPoint$483.$RTT, KMeans.WPoint$483.$RTT)
                                                     });
        
        public x10.rtt.RuntimeType<?> $getRTT() { return $RTT; }
        
        public x10.rtt.Type<?> $getParam(int i) { return null; }
        
        private Object writeReplace() throws java.io.ObjectStreamException {
            return new x10.serialization.SerializationProxy(this);
        }
        
        public static x10.serialization.X10JavaSerializable $_deserialize_body(KMeans.$Closure$18 $_obj, x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$18.class + " calling"); } 
            return $_obj;
        }
        
        public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            KMeans.$Closure$18 $_obj = new KMeans.$Closure$18((java.lang.System[]) null);
            $deserializer.record_reference($_obj);
            return $_deserialize_body($_obj, $deserializer);
        }
        
        public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            
        }
        
        // constructor just for allocation
        public $Closure$18(final java.lang.System[] $dummy) {
            
        }
        
        // dispatcher for method abstract public (Z1,Z2)=>U.operator()(a1:Z1, a2:Z2):U
        public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1, final java.lang.Object a2, final x10.rtt.Type t2) {
            return $apply((KMeans.WPoint$483)a1, (KMeans.WPoint$483)a2);
            
        }
        
        
    
        
        public KMeans.WPoint$483 $apply(final KMeans.WPoint$483 wp1, final KMeans.WPoint$483 wp2) {
            
            //#line 36 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291865 = wp1.valid;
            
            //#line 36 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final boolean t$1291871 = !(t$1291865);
            
            //#line 36 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            if (t$1291871) {
                
                //#line 36 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                return wp2;
            } else {
                
                //#line 37 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final boolean t$1291866 = wp2.valid;
                
                //#line 37 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                final boolean t$1291870 = !(t$1291866);
                
                //#line 37 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                if (t$1291870) {
                    
                    //#line 37 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                    return wp1;
                } else {
                    
                    //#line 39 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                    final double t$1291867 = wp1.dist;
                    
                    //#line 39 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                    final double t$1291868 = wp2.dist;
                    
                    //#line 39 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                    final boolean t$1291869 = ((t$1291867) < (((double)(t$1291868))));
                    
                    //#line 39 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                    if (t$1291869) {
                        
                        //#line 39 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                        return wp1;
                    } else {
                        
                        //#line 40 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
                        return wp2;
                    }
                }
            }
        }
        
        public $Closure$18() {
             {
                
            }
        }
        
    }
    
    @x10.runtime.impl.java.X10Generated
    final public static class $Closure$19 extends x10.core.Ref implements x10.core.fun.Fun_0_1, x10.serialization.X10JavaSerializable
    {
        public static final x10.rtt.RuntimeType<$Closure$19> $RTT = 
            x10.rtt.StaticFunType.<$Closure$19> make($Closure$19.class,
                                                     new x10.rtt.Type[] {
                                                         x10.rtt.ParameterizedType.make(x10.core.fun.Fun_0_1.$RTT, RichPoint.$RTT, x10.rtt.ParameterizedType.make(x10.util.ArrayList.$RTT, RichPoint.$RTT))
                                                     });
        
        public x10.rtt.RuntimeType<?> $getRTT() { return $RTT; }
        
        public x10.rtt.Type<?> $getParam(int i) { return null; }
        
        private Object writeReplace() throws java.io.ObjectStreamException {
            return new x10.serialization.SerializationProxy(this);
        }
        
        public static x10.serialization.X10JavaSerializable $_deserialize_body(KMeans.$Closure$19 $_obj, x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + $Closure$19.class + " calling"); } 
            $_obj.hm = $deserializer.readObject();
            return $_obj;
        }
        
        public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
            KMeans.$Closure$19 $_obj = new KMeans.$Closure$19((java.lang.System[]) null);
            $deserializer.record_reference($_obj);
            return $_deserialize_body($_obj, $deserializer);
        }
        
        public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
            $serializer.write(this.hm);
            
        }
        
        // constructor just for allocation
        public $Closure$19(final java.lang.System[] $dummy) {
            
        }
        
        // dispatcher for method abstract public (Z1)=>U.operator()(a1:Z1):U
        public java.lang.Object $apply(final java.lang.Object a1, final x10.rtt.Type t1) {
            return $apply((RichPoint)a1);
            
        }
        
        // synthetic type for parameter mangling
        public static final class __0$1RichPoint$3x10$util$ArrayList$1RichPoint$2$2 {}
        
    
        
        public x10.util.ArrayList $apply(final RichPoint c) {
            
            //#line 60 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            final x10.util.ArrayList t$1291885 = ((x10.util.HashMap<RichPoint, x10.util.ArrayList<RichPoint>>)this.hm).get__0x10$util$HashMap$$K$G(((RichPoint)(c)));
            
            //#line 60 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/KMeans.x10"
            return t$1291885;
        }
        
        public x10.util.HashMap<RichPoint, x10.util.ArrayList<RichPoint>> hm;
        
        public $Closure$19(final x10.util.HashMap<RichPoint, x10.util.ArrayList<RichPoint>> hm, __0$1RichPoint$3x10$util$ArrayList$1RichPoint$2$2 $dummy) {
             {
                this.hm = ((x10.util.HashMap)(hm));
            }
        }
        
    }
    
}

