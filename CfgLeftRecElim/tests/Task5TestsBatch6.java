package csen1002.tests.task5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task5.CfgLeftRecElim;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task5TestsBatch6 {

	@Test
	public void testCfgLeftRecursionElimination1() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;O;C;Q;W#h;m;s;x;z#S/QSS,SQmO,hOzS,mC,mW;O/OsOzQ,WQx,mWOWS,xSO;C/CCSx,Wm,WsSCx,m,mC;Q/QQ,Qz,SQ,SsSs,hCSS;W/OQOC,SCWm,SWm,WOW,WQCQQ,s");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;O;C;Q;W;S';O';C';Q';W'#h;m;s;x;z#S/QSSS',hOzSS',mCS',mWS';O/WQxO',mWOWSO',xSOO';C/WmC',WsSCxC',mC',mCC';Q/hOzSS'QQ',mCS'QQ',mWS'QQ',hOzSS'sSsQ',mCS'sSsQ',mWS'sSsQ',hCSSQ';W/mWOWSO'QOCW',xSOO'QOCW',hOzSS'QQ'SSS'CWmW',mCS'QQ'SSS'CWmW',mWS'QQ'SSS'CWmW',hOzSS'sSsQ'SSS'CWmW',mCS'sSsQ'SSS'CWmW',mWS'sSsQ'SSS'CWmW',hCSSQ'SSS'CWmW',hOzSS'CWmW',mCS'CWmW',mWS'CWmW',hOzSS'QQ'SSS'WmW',mCS'QQ'SSS'WmW',mWS'QQ'SSS'WmW',hOzSS'sSsQ'SSS'WmW',mCS'sSsQ'SSS'WmW',mWS'sSsQ'SSS'WmW',hCSSQ'SSS'WmW',hOzSS'WmW',mCS'WmW',mWS'WmW',sW';S'/QmOS',e;O'/sOzQO',e;C'/CSxC',e;Q'/QQ',zQ',SSS'QQ',SSS'sSsQ',e;W'/QxO'QOCW',OWW',QCQQW',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination2() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;Y;T;O;R#r;u;v#S/Sr,SuY,r,rSrO;Y/TOOr,TTYYS;T/OuYOS,SuSr,YvSSv,vTvT;O/TYvYS,uOuR;R/OrRY,RYT,RvRvT,TSO,TvY,u");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;Y;T;O;R;S';T';O';R'#r;u;v#S/rS',rSrOS';Y/TOOr,TTYYS;T/OuYOST',rS'uSrT',rSrOS'uSrT',vTvTT';O/rS'uSrT'YvYSO',rSrOS'uSrT'YvYSO',vTvTT'YvYSO',uOuRO';R/rS'uSrT'YvYSO'rRYR',rSrOS'uSrT'YvYSO'rRYR',vTvTT'YvYSO'rRYR',uOuRO'rRYR',rS'uSrT'YvYSO'uYOST'SOR',rSrOS'uSrT'YvYSO'uYOST'SOR',vTvTT'YvYSO'uYOST'SOR',uOuRO'uYOST'SOR',rS'uSrT'SOR',rSrOS'uSrT'SOR',vTvTT'SOR',rS'uSrT'YvYSO'uYOST'vYR',rSrOS'uSrT'YvYSO'uYOST'vYR',vTvTT'YvYSO'uYOST'vYR',uOuRO'uYOST'vYR',rS'uSrT'vYR',rSrOS'uSrT'vYR',vTvTT'vYR',uR';S'/rS',uYS',e;T'/OOrvSSvT',TYYSvSSvT',e;O'/uYOST'YvYSO',e;R'/YTR',vRvTR',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination3() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;K;T;O;V;B;J#i;j;m;s;v#S/BJsT,mO,sS,vK;K/mO,mOBjV;T/BO,TT,TsVs;O/BSO,KsVm,Oj,TOT,jBmKv,jV;V/TjV,VV,VvS;B/OK,i;J/Sm,VOsTS,i,iV,sSSB");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;K;T;O;V;B;J;T';O';V';B'#i;j;m;s;v#S/BJsT,mO,sS,vK;K/mO,mOBjV;T/BOT';O/BSOO',mOsVmO',mOBjVsVmO',BOT'OTO',jBmKvO',jVO';V/BOT'jVV';B/mOsVmO'KB',mOBjVsVmO'KB',jBmKvO'KB',jVO'KB',iB';J/mOsVmO'KB'JsTm,mOBjVsVmO'KB'JsTm,jBmKvO'KB'JsTm,jVO'KB'JsTm,iB'JsTm,mOm,sSm,vKm,mOsVmO'KB'OT'jVV'OsTS,mOBjVsVmO'KB'OT'jVV'OsTS,jBmKvO'KB'OT'jVV'OsTS,jVO'KB'OT'jVV'OsTS,iB'OT'jVV'OsTS,i,iV,sSSB;T'/TT',sVsT',e;O'/jO',e;V'/VV',vSV',e;B'/SOO'KB',OT'OTO'KB',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination4() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;Y;X;P;V#h;j;r;y#S/XPYj,yS;Y/XS,YVPSS,YhYS,jS,r;X/SP,XrY,YYy;P/XXyX,XrYX,hSP,hYy;V/VSV,VXrV,VrPVY,hP,hPS");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;Y;X;P;V;Y';X';V'#h;j;r;y#S/XPYj,yS;Y/XSY',jSY',rY';X/ySPX',jSY'YyX',rY'YyX';P/ySPX'XyX,jSY'YyX'XyX,rY'YyX'XyX,ySPX'rYX,jSY'YyX'rYX,rY'YyX'rYX,hSP,hYy;V/hPV',hPSV';Y'/VPSSY',hYSY',e;X'/PYjPX',rYX',SY'YyX',e;V'/SVV',XrVV',rPVYV',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination5() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;H;E;U;M#a;c;l;s;z#S/EcSz,SEMcS,SsSM;H/SE,lS;E/Hz,MH,MaHs,SES;U/US,UUz,UzSEl,cE,cH,lSSS;M/EMcS,El,aM,aUsHH,c,z");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;H;E;U;M;S';E';U';M'#a;c;l;s;z#S/EcSzS';H/EcSzS'E,lS;E/lSzE',MHE',MaHsE';U/cEU',cHU',lSSSU';M/lSzE'McSM',lSzE'lM',aMM',aUsHHM',cM',zM';S'/EMcSS',sSMS',e;E'/cSzS'EzE',cSzS'ESE',e;U'/SU',UzU',zSElU',e;M'/HE'McSM',aHsE'McSM',HE'lM',aHsE'lM',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination6() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;J;A;Y;N;C#a;h;n;o#S/AhSn,aJN;J/Jn,YoCo,a,hCnJ,nC,oNo;A/aAnJa,oCNA;Y/AC,JS,JhYh,YhYJ,YoSC;N/h,hCANA,hYaC;C/CoCA,CoYhY,JS,YC,aYSYa");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;J;A;Y;N;C;J';Y';C'#a;h;n;o#S/AhSn,aJN;J/YoCoJ',aJ',hCnJJ',nCJ',oNoJ';A/aAnJa,oCNA;Y/aAnJaCY',oCNACY',aJ'SY',hCnJJ'SY',nCJ'SY',oNoJ'SY',aJ'hYhY',hCnJJ'hYhY',nCJ'hYhY',oNoJ'hYhY';N/h,hCANA,hYaC;C/aAnJaCY'oCoJ'SC',oCNACY'oCoJ'SC',aJ'SY'oCoJ'SC',hCnJJ'SY'oCoJ'SC',nCJ'SY'oCoJ'SC',oNoJ'SY'oCoJ'SC',aJ'hYhY'oCoJ'SC',hCnJJ'hYhY'oCoJ'SC',nCJ'hYhY'oCoJ'SC',oNoJ'hYhY'oCoJ'SC',aJ'SC',hCnJJ'SC',nCJ'SC',oNoJ'SC',aAnJaCY'CC',oCNACY'CC',aJ'SY'CC',hCnJJ'SY'CC',nCJ'SY'CC',oNoJ'SY'CC',aJ'hYhY'CC',hCnJJ'hYhY'CC',nCJ'hYhY'CC',oNoJ'hYhY'CC',aYSYaC';J'/nJ',e;Y'/oCoJ'SY',oCoJ'hYhY',hYJY',oSCY',e;C'/oCAC',oYhYC',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination7() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;V;Y;E;J#f;g;l;m#S/Jl,SElJV,SSgVg,VSEJY,lEE,lVlVm;V/YSS,gYlS;Y/YE,YlJEm,mJS;E/JSf,SY,VJE,VSg,YVVf,gElE;J/SJ,Sg,SmEJl,VJEVf,YSgJS,YfJm");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;V;Y;E;J;S';Y';J'#f;g;l;m#S/JlS',VSEJYS',lEES',lVlVmS';V/YSS,gYlS;Y/mJSY';E/JSf,JlS'Y,mJSY'SSSEJYS'Y,gYlSSEJYS'Y,lEES'Y,lVlVmS'Y,mJSY'SSJE,gYlSJE,mJSY'SSSg,gYlSSg,mJSY'VVf,gElE;J/mJSY'SSSEJYS'JJ',gYlSSEJYS'JJ',lEES'JJ',lVlVmS'JJ',mJSY'SSSEJYS'gJ',gYlSSEJYS'gJ',lEES'gJ',lVlVmS'gJ',mJSY'SSSEJYS'mEJlJ',gYlSSEJYS'mEJlJ',lEES'mEJlJ',lVlVmS'mEJlJ',mJSY'SSJEVfJ',gYlSJEVfJ',mJSY'SgJSJ',mJSY'fJmJ';S'/ElJVS',SgVgS',e;Y'/EY',lJEmY',e;J'/lS'JJ',lS'gJ',lS'mEJlJ',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination8() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;M;T;I;G#a;c;h;v;x#S/SG,STG,xIh;M/SGTxM,STaTh,ShITM,vSS;T/IGSv,hMc,xGGM;I/Gv,IIx,IhS,vT;G/ISM,SScS,Tc,cI,cM,vII");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;M;T;I;G;S';I';G'#a;c;h;v;x#S/xIhS';M/xIhS'GTxM,xIhS'TaTh,xIhS'hITM,vSS;T/IGSv,hMc,xGGM;I/GvI',vTI';G/vTI'SMG',xIhS'ScSG',vTI'GSvcG',hMccG',xGGMcG',cIG',cMG',vIIG';S'/GS',TGS',e;I'/IxI',hSI',e;G'/vI'SMG',vI'GSvcG',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination9() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;I;O;M;Y;A;E#b;j;l;t;w#S/Iw,SEMOM,SISO,SISSl,tO;I/IEjYb,ItAb,YOjAA,wOMO;O/SYMjY,bMlAI;M/IjSA,MI,MwEO,SAY,Yb;Y/EIAbO,IEt,b,lYO,t,w;A/EAEI,ESE,lA,lAYtI;E/MEwOw,Ob,l,tY");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;I;O;M;Y;A;E;S';I';M';Y';E'#b;j;l;t;w#S/IwS',tOS';I/YOjAAI',wOMOI';O/YOjAAI'wS'YMjY,wOMOI'wS'YMjY,tOS'YMjY,bMlAI;M/YOjAAI'jSAM',wOMOI'jSAM',YOjAAI'wS'AYM',wOMOI'wS'AYM',tOS'AYM',YbM';Y/EIAbOY',wOMOI'EtY',bY',lYOY',tY',wY';A/EAEI,ESE,lA,lAYtI;E/wOMOI'EtY'OjAAI'jSAM'EwOwE',bY'OjAAI'jSAM'EwOwE',lYOY'OjAAI'jSAM'EwOwE',tY'OjAAI'jSAM'EwOwE',wY'OjAAI'jSAM'EwOwE',wOMOI'jSAM'EwOwE',wOMOI'EtY'OjAAI'wS'AYM'EwOwE',bY'OjAAI'wS'AYM'EwOwE',lYOY'OjAAI'wS'AYM'EwOwE',tY'OjAAI'wS'AYM'EwOwE',wY'OjAAI'wS'AYM'EwOwE',wOMOI'wS'AYM'EwOwE',tOS'AYM'EwOwE',wOMOI'EtY'bM'EwOwE',bY'bM'EwOwE',lYOY'bM'EwOwE',tY'bM'EwOwE',wY'bM'EwOwE',wOMOI'EtY'OjAAI'wS'YMjYbE',bY'OjAAI'wS'YMjYbE',lYOY'OjAAI'wS'YMjYbE',tY'OjAAI'wS'YMjYbE',wY'OjAAI'wS'YMjYbE',wOMOI'wS'YMjYbE',tOS'YMjYbE',bMlAIbE',lE',tYE';S'/EMOMS',ISOS',ISSlS',e;I'/EjYbI',tAbI',e;M'/IM',wEOM',e;Y'/OjAAI'EtY',e;E'/IAbOY'OjAAI'jSAM'EwOwE',IAbOY'OjAAI'wS'AYM'EwOwE',IAbOY'bM'EwOwE',IAbOY'OjAAI'wS'YMjYbE',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination10() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;D;V;W;U#b;m;q;s#S/DWqS,b,sDmU;D/Um,VDSW,sSbV;V/DUU,USDbV,UqDS,VD,Vs,WVm;W/DSWDS,VSsUb,WUbD,bW,qUW;U/DqWsW,USSD,UW,WsDWb,bVWm");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;D;V;W;U;V';W';U'#b;m;q;s#S/DWqS,b,sDmU;D/Um,VDSW,sSbV;V/UmUUV',sSbVUUV',USDbVV',UqDSV',WVmV';W/UmSWDSW',UmUUV'DSWSWDSW',sSbVUUV'DSWSWDSW',USDbVV'DSWSWDSW',UqDSV'DSWSWDSW',sSbVSWDSW',UmUUV'SsUbW',sSbVUUV'SsUbW',USDbVV'SsUbW',UqDSV'SsUbW',bWW',qUWW';U/sSbVUUV'DSWqWsWU',sSbVUUV'DSWSWDSW'VmV'DSWqWsWU',sSbVSWDSW'VmV'DSWqWsWU',sSbVUUV'SsUbW'VmV'DSWqWsWU',bWW'VmV'DSWqWsWU',qUWW'VmV'DSWqWsWU',sSbVqWsWU',sSbVUUV'DSWSWDSW'sDWbU',sSbVSWDSW'sDWbU',sSbVUUV'SsUbW'sDWbU',bWW'sDWbU',qUWW'sDWbU',bVWmU';V'/DSWUUV',DV',sV',e;W'/VmV'DSWSWDSW',VmV'SsUbW',UbDW',e;U'/mqWsWU',mUUV'DSWqWsWU',SDbVV'DSWqWsWU',qDSV'DSWqWsWU',mSWDSW'VmV'DSWqWsWU',mUUV'DSWSWDSW'VmV'DSWqWsWU',SDbVV'DSWSWDSW'VmV'DSWqWsWU',qDSV'DSWSWDSW'VmV'DSWqWsWU',mUUV'SsUbW'VmV'DSWqWsWU',SDbVV'SsUbW'VmV'DSWqWsWU',qDSV'SsUbW'VmV'DSWqWsWU',SSDU',WU',mSWDSW'sDWbU',mUUV'DSWSWDSW'sDWbU',SDbVV'DSWSWDSW'sDWbU',qDSV'DSWSWDSW'sDWbU',mUUV'SsUbW'sDWbU',SDbVV'SsUbW'sDWbU',qDSV'SsUbW'sDWbU',e", cfgLeftRecElim.toString());
	}

}