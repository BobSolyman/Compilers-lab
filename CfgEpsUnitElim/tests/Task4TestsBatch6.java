package csen1002.tests.task4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task4.CfgEpsUnitElim;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task4TestsBatch6 {

	@Test
	public void testCfgEpsilonRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;M;X;U;I;Q#q;r;s;u#S/r,rIUQ;M/Mr,UQr,X,e,u,uQ;X/SuUsQ,Xq,e;U/I,M,QuUs,US,rXsI,uIq;I/S,U,UMSX,XIQuU,e,rMUu,u;Q/UQuQ,sISs,uSuXs");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;M;X;U;I;Q#q;r;s;u#S/r,rIQ,rIUQ,rQ,rUQ;M/Mr,Qr,UQr,X,r,u,uQ;X/SuUsQ,SusQ,Xq,q;U/I,M,QuUs,Qus,S,US,rXs,rXsI,rs,rsI,uIq,uq;I/IQu,IQuU,MS,MSX,Qu,QuU,S,SX,U,UMS,UMSX,US,USX,XIQu,XIQuU,XQu,XQuU,rMUu,rMu,rUu,ru,u;Q/QuQ,UQuQ,sISs,sSs,uSuXs,uSus", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;Y;W;H;K;N#n;u;v#S/SYN,WnYu,v;Y/H,K,NN,S,Wn,Y,YK;W/K,W,nKvSW,nN;H/S,YKHvY,YvY,e,vWNHu,vWWn;K/S,YnYn,e,vWHW;N/H,S,WKW,YKSS");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;Y;W;H;K;N#n;u;v#S/S,SN,SY,SYN,WnYu,Wnu,nYu,nu,v;Y/H,K,N,NN,S,Wn,Y,YK,n;W/K,W,n,nKvS,nKvSW,nN,nvS,nvSW;H/Hv,HvY,KHv,KHvY,Kv,KvY,S,YHv,YHvY,YKHv,YKHvY,YKv,YKvY,Yv,YvY,v,vHu,vNHu,vNu,vWHu,vWNHu,vWNu,vWWn,vWn,vWu,vY,vn,vu;K/S,YnYn,Ynn,nYn,nn,v,vH,vHW,vW,vWH,vWHW,vWW;N/H,K,KSS,KW,S,SS,W,WK,WKW,WW,YKSS,YSS", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;J;F;C;M;P;Y#c;k;o;v#S/C,Y,YFPv,vP;J/P,PoJkS,S,e,oF;F/P,SkJo,cMY,e,kF;C/CJ,Fo,o;M/FMF,vPPk;P/F,J,PkJv,YcMJY,e,oMv;Y/JoJ,MvC,k");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;J;F;C;M;P;Y#c;k;o;v#S/C,Y,YFPv,YFv,YPv,Yv,v,vP;J/P,PoJkS,PokS,S,o,oF,oJkS,okS;F/P,SkJo,Sko,cMY,k,kF;C/C,CJ,Fo,o;M/FM,FMF,M,MF,vPPk,vPk,vk;P/F,J,PkJv,Pkv,YcMJY,YcMY,kJv,kv,oMv;Y/Jo,JoJ,MvC,k,o,oJ", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;C;I;Y;T#h;r;z#S/C,S,SCYYr,YTrIY;C/C,YhY,hCYSr;I/Ir,TYTSr,hS;Y/CCITI,Y,hC,hYSz,r;T/Y,YIY");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;C;I;Y;T#h;r;z#S/SCYYr,YTrIY,YhY,hCYSr;C/YhY,hCYSr;I/Ir,TYTSr,hS;Y/CCITI,hC,hYSz,r;T/CCITI,YIY,hC,hYSz,r", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;M;Q;A;N;B#c;o;u#S/A,BQoAc,SAN;M/McQu,N,Qc,u;Q/NMBoB,QcBN,S;A/N,NNN,QBcN,oBoN;N/BQNuN,QNcBo,S,uBoM;B/Q,S,oSuNN");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;M;Q;A;N;B#c;o;u#S/BQNuN,BQoAc,NNN,QBcN,QNcBo,SAN,oBoN,uBoM;M/BQNuN,BQoAc,McQu,NNN,QBcN,QNcBo,Qc,SAN,oBoN,u,uBoM;Q/BQNuN,BQoAc,NMBoB,NNN,QBcN,QNcBo,QcBN,SAN,oBoN,uBoM;A/BQNuN,BQoAc,NNN,QBcN,QNcBo,SAN,oBoN,uBoM;N/BQNuN,BQoAc,NNN,QBcN,QNcBo,SAN,oBoN,uBoM;B/BQNuN,BQoAc,NMBoB,NNN,QBcN,QNcBo,QcBN,SAN,oBoN,oSuNN,uBoM", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;W;R;E;L#d;h;m;n;z#S/ERLS,LRS,RzW,S;W/E,EELEL,LWRL,R,SL;R/ERnRn,S,dWLLW,hLdER,zRmL;E/E,LhLW,R,WEdR,dSSLd,hWRWL;L/EdWS,R,Rh,WLmSn");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;W;R;E;L#d;h;m;n;z#S/ERLS,LRS,RzW;W/EELEL,ERLS,ERnRn,LRS,LWRL,LhLW,RzW,SL,WEdR,dSSLd,dWLLW,hLdER,hWRWL,zRmL;R/ERLS,ERnRn,LRS,RzW,dWLLW,hLdER,zRmL;E/ERLS,ERnRn,LRS,LhLW,RzW,WEdR,dSSLd,dWLLW,hLdER,hWRWL,zRmL;L/ERLS,ERnRn,EdWS,LRS,Rh,RzW,WLmSn,dWLLW,hLdER,zRmL", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;M;W;R;K;Y;T#n;o;p;w#S/MSK,nMwRK,wST,wY;M/RoMoR,WWT,nRnY;W/SwKo,n,w;R/RTM,S,wKnM;K/KWTn,M,e,pWS;Y/RnTM,S,e,nMo;T/RSY,W,WwMW,wT");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;M;W;R;K;Y;T#n;o;p;w#S/MS,MSK,nMwR,nMwRK,w,wST,wY;M/RoMoR,WWT,nRn,nRnY;W/SwKo,Swo,n,w;R/MS,MSK,RTM,nMwR,nMwRK,w,wKnM,wST,wY,wnM;K/KWTn,RoMoR,WTn,WWT,nRn,nRnY,pWS;Y/MS,MSK,RnTM,nMo,nMwR,nMwRK,w,wST,wY;T/RS,RSY,SwKo,Swo,WwMW,n,w,wT", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;C;M;D;T#h;k;u#S/hD,hDCCT;C/D,M,Tu,e,uT;M/C,MkMCk,S,T,e,kTD;D/M,T,TTM,TkCTS,e;T/S,hD,kM");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;C;M;D;T#h;k;u#S/h,hCCT,hCT,hD,hDCCT,hDCT,hDT,hT;C/MkCk,MkMCk,MkMk,Mkk,TT,TTM,TkCTS,TkTS,Tu,h,hCCT,hCT,hD,hDCCT,hDCT,hDT,hT,k,kCk,kM,kMCk,kMk,kT,kTD,kk,uT;M/MkCk,MkMCk,MkMk,Mkk,TT,TTM,TkCTS,TkTS,Tu,h,hCCT,hCT,hD,hDCCT,hDCT,hDT,hT,k,kCk,kM,kMCk,kMk,kT,kTD,kk,uT;D/MkCk,MkMCk,MkMk,Mkk,TT,TTM,TkCTS,TkTS,Tu,h,hCCT,hCT,hD,hDCCT,hDCT,hDT,hT,k,kCk,kM,kMCk,kMk,kT,kTD,kk,uT;T/h,hCCT,hCT,hD,hDCCT,hDCT,hDT,hT,k,kM", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;W;A;I;Y;J;N#a;b;i;k#S/SNk,YkWaA,bIWbA;W/Ja,S,Y,bS;A/A,IYAb,JIiI,NJYaS,e;I/SI,bNb,bYI;Y/S,bYkJJ,e;J/ANWa,I,JNbS,NNSYk,S,WkYYk,e;N/NiSW,WJ,YAi,iNWkS");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;W;A;I;Y;J;N#a;b;i;k#S/SNk,Sk,YkWa,YkWaA,Yka,YkaA,bIWb,bIWbA,bIb,bIbA,kWa,kWaA,ka,kaA;W/Ja,SNk,Sk,YkWa,YkWaA,Yka,YkaA,a,bIWb,bIWbA,bIb,bIbA,bS,bYk,bYkJ,bYkJJ,bk,bkJ,bkJJ,kWa,kWaA,ka,kaA;A/IAb,IYAb,IYb,Ib,IiI,JIiI,JYaS,JaS,NJYaS,NJaS,NYaS,NaS,YaS,aS;I/SI,bI,bNb,bYI,bb;Y/SNk,Sk,YkWa,YkWaA,Yka,YkaA,bIWb,bIWbA,bIb,bIbA,bYk,bYkJ,bYkJJ,bk,bkJ,bkJJ,kWa,kWaA,ka,kaA;J/ANWa,ANa,AWa,Aa,JNbS,JbS,NNSYk,NNSk,NSYk,NSk,NWa,Na,NbS,SI,SNk,SYk,Sk,Wa,WkYYk,WkYk,Wkk,YkWa,YkWaA,Yka,YkaA,a,bI,bIWb,bIWbA,bIb,bIbA,bNb,bS,bYI,bb,kWa,kWaA,kYYk,kYk,ka,kaA,kk;N/ANWa,ANa,AWa,Aa,Ai,JNbS,Ja,JbS,NNSYk,NNSk,NSYk,NSk,NWa,Na,NbS,NiS,NiSW,SI,SNk,SYk,Sk,WJ,Wa,WkYYk,WkYk,Wkk,YAi,Yi,YkWa,YkWaA,Yka,YkaA,a,bI,bIWb,bIWbA,bIb,bIbA,bNb,bS,bYI,bYk,bYkJ,bYkJJ,bb,bk,bkJ,bkJJ,i,iNWkS,iNkS,iS,iSW,iWkS,ikS,kWa,kWaA,kYYk,kYk,ka,kaA,kk", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination4() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;N;A;G;X;Y;R#b;d;l;t;u#S/N,SSdXl,uSNdN;N/N,YRtAX,b,lYNXX;A/bGG,dGXXS,e,uGAYX,uNGS;G/X,Y,lYNS;X/A,S,X,dRSlY,tSbRt;Y/AY,R,Y,e,t;R/YuXY,dX");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;N;A;G;X;Y;R#b;d;l;t;u#S/Rt,RtA,RtAX,RtX,SSdXl,SSdl,YRt,YRtA,YRtAX,YRtX,b,lN,lNX,lNXX,lYN,lYNX,lYNXX,uSNdN;N/Rt,RtA,RtAX,RtX,YRt,YRtA,YRtAX,YRtX,b,lN,lNX,lNXX,lYN,lYNX,lYNXX;A/b,bG,bGG,dGS,dGXS,dGXXS,dS,dXS,dXXS,u,uA,uAX,uAY,uAYX,uG,uGA,uGAX,uGAY,uGAYX,uGX,uGY,uGYX,uNGS,uNS,uX,uY,uYX;G/AY,Rt,RtA,RtAX,RtX,SSdXl,SSdl,YRt,YRtA,YRtAX,YRtX,Yu,YuX,YuXY,YuY,b,bG,bGG,d,dGS,dGXS,dGXXS,dRSl,dRSlY,dS,dX,dXS,dXXS,lN,lNS,lNX,lNXX,lYN,lYNS,lYNX,lYNXX,t,tSbRt,u,uA,uAX,uAY,uAYX,uG,uGA,uGAX,uGAY,uGAYX,uGX,uGY,uGYX,uNGS,uNS,uSNdN,uX,uXY,uY,uYX;X/Rt,RtA,RtAX,RtX,SSdXl,SSdl,YRt,YRtA,YRtAX,YRtX,b,bG,bGG,dGS,dGXS,dGXXS,dRSl,dRSlY,dS,dXS,dXXS,lN,lNX,lNXX,lYN,lYNX,lYNXX,tSbRt,u,uA,uAX,uAY,uAYX,uG,uGA,uGAX,uGAY,uGAYX,uGX,uGY,uGYX,uNGS,uNS,uSNdN,uX,uY,uYX;Y/AY,Yu,YuX,YuXY,YuY,b,bG,bGG,d,dGS,dGXS,dGXXS,dS,dX,dXS,dXXS,t,u,uA,uAX,uAY,uAYX,uG,uGA,uGAX,uGAY,uGAYX,uGX,uGY,uGYX,uNGS,uNS,uX,uXY,uY,uYX;R/Yu,YuX,YuXY,YuY,d,dX,u,uX,uXY,uY", cfgEpsUnitElim.toString());
	}

}