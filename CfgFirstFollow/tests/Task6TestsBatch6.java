package csen1002.tests.task6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task6.CfgFirstFollow;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task6TestsBatch6 {

	@Test
	public void testCfg1First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;Q;D;G;K;Y#a;d;f;h;o;p;x#S/G,Q,QQSG,f,x,xDGf;Q/GQD,SQKx,e;D/D,QhQ,S,Yf,YhKhQ,e,hGaGo,xD;G/G,oGo,xDdQG;K/KoGfY,Q,e,pYYoS;Y/DGoSS,S,h");
		assertEquals("S/efopx;Q/efopx;D/efhopx;G/ox;K/efopx;Y/efhopx", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg1Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;Q;D;G;K;Y#a;d;f;h;o;p;x#S/G,Q,QQSG,f,x,xDGf;Q/GQD,SQKx,e;D/D,QhQ,S,Yf,YhKhQ,e,hGaGo,xD;G/G,oGo,xDdQG;K/KoGfY,Q,e,pYYoS;Y/DGoSS,S,h");
		assertEquals("S/$dfhopx;Q/$dfhopx;D/$dfhopx;G/$adfhopx;K/hox;Y/fhopx", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg2First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;K;G;F;X;P;L#d;i;p;s;t;u;y#S/F,s,tPG,uX;K/F,KSFiG,LL,e,iFs,tXS;G/KtXdX,uXtFi;F/e,uLG,y;X/F,G,GGGKt,SG;P/F,G,LK,XiGF,iFPF,pS,sXX;L/FsG,FyKt,dGtGG,iLuX,iX,sGXdX");
		assertEquals("S/estuy;K/deistuy;G/distuy;F/euy;X/deistuy;P/deipstuy;L/disuy", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg2Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;K;G;F;X;P;L#d;i;p;s;t;u;y#S/F,s,tPG,uX;K/F,KSFiG,LL,e,iFs,tXS;G/KtXdX,uXtFi;F/e,uLG,y;X/F,G,GGGKt,SG;P/F,G,LK,XiGF,iFPF,pS,sXX;L/FsG,FyKt,dGtGG,iLuX,iX,sGXdX");
		assertEquals("S/$distuy;K/distuy;G/$dipstuy;F/$dipstuy;X/$dipstuy;P/distuy;L/distuy", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg3First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;U;H;G;A;I#a;b;j;m;n;x;y#S/S,nH,x;U/G,SI,bUAIj,jAHUI;H/GbH,GxI,S,SUS,bIm,e;G/GnSS,Gy,S,SSxUI,SnI,e,jS;A/ASHbG,SI,U,bGySS,yGaHU;I/Sj,jHjHS");
		assertEquals("S/nx;U/bejnxy;H/bejnxy;G/ejnxy;A/bejnxy;I/jnx", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg3Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;U;H;G;A;I#a;b;j;m;n;x;y#S/S,nH,x;U/G,SI,bUAIj,jAHUI;H/GbH,GxI,S,SUS,bIm,e;G/GnSS,Gy,S,SSxUI,SnI,e,jS;A/ASHbG,SI,U,bGySS,yGaHU;I/Sj,jHjHS");
		assertEquals("S/$abjmnxy;U/bjnxy;H/$abjmnxy;G/abjnxy;A/bjnxy;I/$abjmnxy", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg4First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;C;F;G;W;E#a;i;p;r;s;t;w#S/EWi,EpFiF,F;C/E,Fp,G,GEF,e,sCE,w;F/SpS,aGEa,e,iWE;G/Fa,GiEC,W,e,sS;W/F,r,wFtCE;E/ErEa,EtGwS");
		assertEquals("S/aeip;C/aeiprsw;F/aeip;G/aeiprsw;W/aeiprw;E/", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg4Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;C;F;G;W;E#a;i;p;r;s;t;w#S/EWi,EpFiF,F;C/E,Fp,G,GEF,e,sCE,w;F/SpS,aGEa,e,iWE;G/Fa,GiEC,W,e,sS;W/F,r,wFtCE;E/ErEa,EtGwS");
		assertEquals("S/$aiprstw;C/iw;F/$aiprstw;G/iw;W/iw;E/$aiprstw", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg5First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;K;X;P;I;F;W#a;i;l;v;x;y;z#S/K,zIXKX,zXx;K/K,SvK,XWvX,e;X/X,aSX,iSyF;P/Fx,P,a,e,iW,vFvFK,x;I/FSKvS,K,P,x;F/Wv,XaWPa,aKxS,e,lF,lWFaS;W/FXW,XFy,XlXK,i,y,yF");
		assertEquals("S/aeivz;K/aeivz;X/ai;P/aeilvxy;I/aeilvxyz;F/aeily;W/aily", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg5Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;K;X;P;I;F;W#a;i;l;v;x;y;z#S/K,zIXKX,zXx;K/K,SvK,XWvX,e;X/X,aSX,iSyF;P/Fx,P,a,e,iW,vFvFK,x;I/FSKvS,K,P,x;F/Wv,XaWPa,aKxS,e,lF,lWFaS;W/FXW,XFy,XlXK,i,y,yF");
		assertEquals("S/$ailvxyz;K/$ailvxyz;X/$ailvxyz;P/ai;I/ai;F/$ailvxyz;W/ailvxy", cfgFirstFollow.follow());
	}

}