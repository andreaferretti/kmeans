import x10.compiler.Native;
import x10.compiler.NativeCPPInclude;
import x10.compiler.NativeCPPCompilationUnit;

@NativeCPPInclude("jsonRead.h")
@NativeCPPCompilationUnit("jsonRead.cc")
public class CJson {
	
	@Native("c++","parse()")
	static native def initialize():void;
	
	@Native("c++","nextX()")
	static native def getNextX():Double;

	@Native("c++","nextY()")
	static native def getNextY():Double;

}