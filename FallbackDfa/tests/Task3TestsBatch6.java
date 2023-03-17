package csen1002.tests.task3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task3.FallbackDfa;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task3TestsBatch6 {

	@Test
	public void testFallbackDfa1String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#d;z#0,d,9;0,z,2;1,d,10;1,z,7;2,d,0;2,z,1;3,d,2;3,z,0;4,d,12;4,z,3;5,d,11;5,z,2;6,d,7;6,z,3;7,d,12;7,z,8;8,d,1;8,z,7;9,d,4;9,z,3;10,d,6;10,z,7;11,d,3;11,z,2;12,d,10;12,z,5#2#0;5;7;8");
		assertEquals("zdzdzdzddzdzdz,7;d,0;d,0;d,0", fallbackDfa.run("zdzdzdzddzdzdzddd"));
	}

	@Test
	public void testFallbackDfa1String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#d;z#0,d,9;0,z,2;1,d,10;1,z,7;2,d,0;2,z,1;3,d,2;3,z,0;4,d,12;4,z,3;5,d,11;5,z,2;6,d,7;6,z,3;7,d,12;7,z,8;8,d,1;8,z,7;9,d,4;9,z,3;10,d,6;10,z,7;11,d,3;11,z,2;12,d,10;12,z,5#2#0;5;7;8");
		assertEquals("zzzddzzdz,7;d,0;d,0;d,0", fallbackDfa.run("zzzddzzdzddd"));
	}

	@Test
	public void testFallbackDfa1String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#d;z#0,d,9;0,z,2;1,d,10;1,z,7;2,d,0;2,z,1;3,d,2;3,z,0;4,d,12;4,z,3;5,d,11;5,z,2;6,d,7;6,z,3;7,d,12;7,z,8;8,d,1;8,z,7;9,d,4;9,z,3;10,d,6;10,z,7;11,d,3;11,z,2;12,d,10;12,z,5#2#0;5;7;8");
		assertEquals("zdz,7;d,0;d,0;d,0;z,1", fallbackDfa.run("zdzdddz"));
	}

	@Test
	public void testFallbackDfa1String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#d;z#0,d,9;0,z,2;1,d,10;1,z,7;2,d,0;2,z,1;3,d,2;3,z,0;4,d,12;4,z,3;5,d,11;5,z,2;6,d,7;6,z,3;7,d,12;7,z,8;8,d,1;8,z,7;9,d,4;9,z,3;10,d,6;10,z,7;11,d,3;11,z,2;12,d,10;12,z,5#2#0;5;7;8");
		assertEquals("zdzdzzddddz,5;d,0;zz,7;d,0", fallbackDfa.run("zdzdzzddddzdzzd"));
	}

	@Test
	public void testFallbackDfa1String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#d;z#0,d,9;0,z,2;1,d,10;1,z,7;2,d,0;2,z,1;3,d,2;3,z,0;4,d,12;4,z,3;5,d,11;5,z,2;6,d,7;6,z,3;7,d,12;7,z,8;8,d,1;8,z,7;9,d,4;9,z,3;10,d,6;10,z,7;11,d,3;11,z,2;12,d,10;12,z,5#2#0;5;7;8");
		assertEquals("zzddzzdzdzdzd,0;d,0;d,0;d,0;d,0", fallbackDfa.run("zzddzzdzdzdzddddd"));
	}

	@Test
	public void testFallbackDfa2String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#g;k;w#0,g,11;0,k,12;0,w,2;1,g,10;1,k,6;1,w,1;2,g,0;2,k,9;2,w,12;3,g,9;3,k,4;3,w,5;4,g,1;4,k,13;4,w,9;5,g,12;5,k,4;5,w,7;6,g,13;6,k,13;6,w,8;7,g,2;7,k,9;7,w,10;8,g,4;8,k,10;8,w,6;9,g,10;9,k,4;9,w,9;10,g,8;10,k,1;10,w,13;11,g,12;11,k,9;11,w,3;12,g,12;12,k,12;12,w,12;13,g,10;13,k,5;13,w,5#5#0;1;9;11;13");
		assertEquals("wwgggkg,13;wgg,0;www,13;kgkwgkkwgk,9", fallbackDfa.run("wwgggkgwggwwwkgkwgkkwgk"));
	}

	@Test
	public void testFallbackDfa2String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#g;k;w#0,g,11;0,k,12;0,w,2;1,g,10;1,k,6;1,w,1;2,g,0;2,k,9;2,w,12;3,g,9;3,k,4;3,w,5;4,g,1;4,k,13;4,w,9;5,g,12;5,k,4;5,w,7;6,g,13;6,k,13;6,w,8;7,g,2;7,k,9;7,w,10;8,g,4;8,k,10;8,w,6;9,g,10;9,k,4;9,w,9;10,g,8;10,k,1;10,w,13;11,g,12;11,k,9;11,w,3;12,g,12;12,k,12;12,w,12;13,g,10;13,k,5;13,w,5#5#0;1;9;11;13");
		assertEquals("wgg,0;kgkggw,13;wwgwwwwwkgggk,13;g,12", fallbackDfa.run("wggkgkggwwwgwwwwwkgggkg"));
	}

	@Test
	public void testFallbackDfa2String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#g;k;w#0,g,11;0,k,12;0,w,2;1,g,10;1,k,6;1,w,1;2,g,0;2,k,9;2,w,12;3,g,9;3,k,4;3,w,5;4,g,1;4,k,13;4,w,9;5,g,12;5,k,4;5,w,7;6,g,13;6,k,13;6,w,8;7,g,2;7,k,9;7,w,10;8,g,4;8,k,10;8,w,6;9,g,10;9,k,4;9,w,9;10,g,8;10,k,1;10,w,13;11,g,12;11,k,9;11,w,3;12,g,12;12,k,12;12,w,12;13,g,10;13,k,5;13,w,5#5#0;1;9;11;13");
		assertEquals("wgg,0;kwkggkkgggwk,13;wgk,9;g,12", fallbackDfa.run("wggkwkggkkgggwkwgkg"));
	}

	@Test
	public void testFallbackDfa2String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#g;k;w#0,g,11;0,k,12;0,w,2;1,g,10;1,k,6;1,w,1;2,g,0;2,k,9;2,w,12;3,g,9;3,k,4;3,w,5;4,g,1;4,k,13;4,w,9;5,g,12;5,k,4;5,w,7;6,g,13;6,k,13;6,w,8;7,g,2;7,k,9;7,w,10;8,g,4;8,k,10;8,w,6;9,g,10;9,k,4;9,w,9;10,g,8;10,k,1;10,w,13;11,g,12;11,k,9;11,w,3;12,g,12;12,k,12;12,w,12;13,g,10;13,k,5;13,w,5#5#0;1;9;11;13");
		assertEquals("kk,13;wgkgw,13;wgkwwgkwgk,1;k,4", fallbackDfa.run("kkwgkgwwgkwwgkwgkk"));
	}

	@Test
	public void testFallbackDfa2String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#g;k;w#0,g,11;0,k,12;0,w,2;1,g,10;1,k,6;1,w,1;2,g,0;2,k,9;2,w,12;3,g,9;3,k,4;3,w,5;4,g,1;4,k,13;4,w,9;5,g,12;5,k,4;5,w,7;6,g,13;6,k,13;6,w,8;7,g,2;7,k,9;7,w,10;8,g,4;8,k,10;8,w,6;9,g,10;9,k,4;9,w,9;10,g,8;10,k,1;10,w,13;11,g,12;11,k,9;11,w,3;12,g,12;12,k,12;12,w,12;13,g,10;13,k,5;13,w,5#5#0;1;9;11;13");
		assertEquals("wkwwgkkk,13;kgwkg,13;kg,1;kw,9", fallbackDfa.run("wkwwgkkkkgwkgkgkw"));
	}

}