@x10.runtime.impl.java.X10Generated
public class RichPoint extends x10.core.Struct implements x10.serialization.X10JavaSerializable
{
    public static final x10.rtt.RuntimeType<RichPoint> $RTT = 
        x10.rtt.NamedStructType.<RichPoint> make("RichPoint",
                                                 RichPoint.class,
                                                 new x10.rtt.Type[] {
                                                     x10.rtt.Types.STRUCT
                                                 });
    
    public x10.rtt.RuntimeType<?> $getRTT() { return $RTT; }
    
    public x10.rtt.Type<?> $getParam(int i) { return null; }
    
    private Object writeReplace() throws java.io.ObjectStreamException {
        return new x10.serialization.SerializationProxy(this);
    }
    
    public static x10.serialization.X10JavaSerializable $_deserialize_body(RichPoint $_obj, x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
        if (x10.runtime.impl.java.Runtime.TRACE_SER) { x10.runtime.impl.java.Runtime.printTraceMessage("X10JavaSerializable: $_deserialize_body() of " + RichPoint.class + " calling"); } 
        $_obj.x = $deserializer.readDouble();
        $_obj.y = $deserializer.readDouble();
        return $_obj;
    }
    
    public static x10.serialization.X10JavaSerializable $_deserializer(x10.serialization.X10JavaDeserializer $deserializer) throws java.io.IOException {
        RichPoint $_obj = new RichPoint((java.lang.System[]) null);
        return $_deserialize_body($_obj, $deserializer);
    }
    
    public void $_serialize(x10.serialization.X10JavaSerializer $serializer) throws java.io.IOException {
        $serializer.write(this.x);
        $serializer.write(this.y);
        
    }
    
    // zero value constructor
    public RichPoint(final java.lang.System $dummy) { this.x = 0.0; this.y = 0.0; }
    
    // constructor just for allocation
    public RichPoint(final java.lang.System[] $dummy) {
        
    }
    
    

    
    //#line 2 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    public double x;
    
    //#line 3 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    public double y;
    
    
    //#line 5 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    // creation method for java code (1-phase java constructor)
    public RichPoint(final double x, final double y) {
        this((java.lang.System[]) null);
        RichPoint$$init$S(x, y);
    }
    
    // constructor for non-virtual call
    final public RichPoint RichPoint$$init$S(final double x, final double y) {
         {
            
            //#line 5 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            this.__fieldInitializers_RichPoint();
            
            //#line 6 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            this.x = x;
            
            //#line 6 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            this.y = y;
        }
        return this;
    }
    
    
    
    //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public RichPoint $plus(final RichPoint p2) {
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291500 = this.x;
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291501 = p2.x;
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291504 = ((t$1291500) + (((double)(t$1291501))));
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291502 = this.y;
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291503 = p2.y;
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291505 = ((t$1291502) + (((double)(t$1291503))));
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final RichPoint t$1291506 = new RichPoint((java.lang.System[]) null).RichPoint$$init$S(t$1291504, t$1291505);
        
        //#line 9 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return t$1291506;
    }
    
    
    //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public RichPoint $minus(final RichPoint p2) {
        
        //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291507 = this.x;
        
        //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291508 = p2.x;
        
        //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291511 = ((t$1291507) - (((double)(t$1291508))));
        
        //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291509 = this.y;
        
        //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291510 = p2.y;
        
        //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291512 = ((t$1291509) - (((double)(t$1291510))));
        
        //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final RichPoint t$1291513 = new RichPoint((java.lang.System[]) null).RichPoint$$init$S(t$1291511, t$1291512);
        
        //#line 11 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return t$1291513;
    }
    
    
    //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public RichPoint $over(final double d) {
        
        //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291514 = this.x;
        
        //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291516 = ((t$1291514) / (((double)(d))));
        
        //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291515 = this.y;
        
        //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291517 = ((t$1291515) / (((double)(d))));
        
        //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final RichPoint t$1291518 = new RichPoint((java.lang.System[]) null).RichPoint$$init$S(t$1291516, t$1291517);
        
        //#line 13 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return t$1291518;
    }
    
    
    //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public double modulus$O() {
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291519 = this.x;
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291521 = KMeans.sq$O((double)(t$1291519));
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291520 = this.y;
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291522 = KMeans.sq$O((double)(t$1291520));
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291523 = ((t$1291521) + (((double)(t$1291522))));
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291524 = java.lang.Math.sqrt(((double)(t$1291523)));
        
        //#line 15 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return t$1291524;
    }
    
    
    //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public java.lang.String typeName() {
        try {
            return x10.rtt.Types.typeName(this);
        }
        catch (java.lang.Throwable exc$1292044) {
            throw x10.runtime.impl.java.ThrowableUtils.ensureX10Exception(exc$1292044);
        }
        
    }
    
    
    
    //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public java.lang.String toString() {
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final java.lang.String t$1291525 = "struct RichPoint: x=";
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291526 = this.x;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final java.lang.String t$1291527 = ((t$1291525) + ((x10.core.Double.$box(t$1291526))));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final java.lang.String t$1291528 = ((t$1291527) + (" y="));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291529 = this.y;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final java.lang.String t$1291530 = ((t$1291528) + ((x10.core.Double.$box(t$1291529))));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return t$1291530;
    }
    
    
    //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public int hashCode() {
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        int result = 1;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final int t$1291531 = result;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final int t$1291533 = ((8191) * (((int)(t$1291531))));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291532 = this.x;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final int t$1291534 = x10.rtt.Types.hashCode(t$1291532);
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final int t$1291535 = ((t$1291533) + (((int)(t$1291534))));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        result = t$1291535;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final int t$1291536 = result;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final int t$1291538 = ((8191) * (((int)(t$1291536))));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291537 = this.y;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final int t$1291539 = x10.rtt.Types.hashCode(t$1291537);
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final int t$1291540 = ((t$1291538) + (((int)(t$1291539))));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        result = t$1291540;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final int t$1291541 = result;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return t$1291541;
    }
    
    
    //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public boolean equals(java.lang.Object other) {
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final java.lang.Object t$1291542 = ((java.lang.Object)(other));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final boolean t$1291543 = RichPoint.$RTT.isInstance(t$1291542);
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final boolean t$1291544 = !(t$1291543);
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        if (t$1291544) {
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            return false;
        }
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final java.lang.Object t$1291545 = ((java.lang.Object)(other));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final RichPoint t$1291546 = ((RichPoint)x10.rtt.Types.asStruct(RichPoint.$RTT,t$1291545));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final boolean t$1291547 = this.equals$O(((RichPoint)(t$1291546)));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return t$1291547;
    }
    
    
    //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public boolean equals$O(RichPoint other) {
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291549 = this.x;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final RichPoint t$1291548 = ((RichPoint)(other));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291550 = t$1291548.x;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        boolean t$1291554 = ((double) t$1291549) == ((double) t$1291550);
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        if (t$1291554) {
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            final double t$1291552 = this.y;
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            final RichPoint t$1291551 = ((RichPoint)(other));
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            final double t$1291553 = t$1291551.y;
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            t$1291554 = ((double) t$1291552) == ((double) t$1291553);
        }
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final boolean t$1291555 = t$1291554;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return t$1291555;
    }
    
    
    //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public boolean _struct_equals$O(java.lang.Object other) {
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final java.lang.Object t$1291556 = ((java.lang.Object)(other));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final boolean t$1291557 = RichPoint.$RTT.isInstance(t$1291556);
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final boolean t$1291558 = !(t$1291557);
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        if (t$1291558) {
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            return false;
        }
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final java.lang.Object t$1291559 = ((java.lang.Object)(other));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final RichPoint t$1291560 = ((RichPoint)x10.rtt.Types.asStruct(RichPoint.$RTT,t$1291559));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final boolean t$1291561 = this._struct_equals$O(((RichPoint)(t$1291560)));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return t$1291561;
    }
    
    
    //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public boolean _struct_equals$O(RichPoint other) {
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291563 = this.x;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final RichPoint t$1291562 = ((RichPoint)(other));
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final double t$1291564 = t$1291562.x;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        boolean t$1291568 = ((double) t$1291563) == ((double) t$1291564);
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        if (t$1291568) {
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            final double t$1291566 = this.y;
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            final RichPoint t$1291565 = ((RichPoint)(other));
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            final double t$1291567 = t$1291565.y;
            
            //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
            t$1291568 = ((double) t$1291566) == ((double) t$1291567);
        }
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        final boolean t$1291569 = t$1291568;
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return t$1291569;
    }
    
    
    //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public RichPoint RichPoint$$this$RichPoint() {
        
        //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
        return RichPoint.this;
    }
    
    
    //#line 1 "/home/andrea/x10dt/workspace/kmeans-x10-java/src/RichPoint.x10"
    final public void __fieldInitializers_RichPoint() {
        
    }
}

