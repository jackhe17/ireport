package com.thinkgem.jeesite.modules.report.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class DayReport extends DataEntity<DayReport> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -962148974396642437L;
	private String officeId;
	private String reportDate;//日期
	private String weather;//天气
	private String temperature;//温度
	private String humidity;//湿度
	private String earlyWorker;//早班
	private String middleWorker;//中班
	private String lastWorker;//晚班
	private String dwt1;//日处理水量1
	private String dwt2;//日处理水量2
	private String sq1;//污泥量1
	private String sq2;//污泥量2
	private String meter;//电表读数
	private String electricity;//用电量
	private String waterMeter;//自来水表读数
	private String water;//用水量
	private String pac;//PAC
	private String ipra;//铁盐/除磷剂
	private String pam1;//PAM-
	private String pam2;//PAM+
	private String disin;//消毒药剂
	private String stopwatertime;//停进水时间
	private String stopreason;//停产原因
	private String recoverytime;//恢复进水时间
	private String reportDesc;//情况说明
	//COD
	private String cod1;//
	private String cod2;//
	private String cod3;//
	private String cod4;//
	private String cod5;//
	private String cod6;//
	private String cod7;//
	private String cod8;//
	//氨氮
	private String an1;//
	private String an2;//
	private String an3;//
	private String an4;//
	private String an5;//
	private String an6;//
	private String an7;//
	private String an8;//
	//TN
	private String tn1;//
	private String tn2;//
	private String tn3;//
	private String tn4;//
	private String tn5;//
	private String tn6;//
	private String tn7;//
	private String tn8;//
	//TP
	private String tp1;//
	private String tp2;//
	private String tp3;//
	private String tp4;//
	private String tp5;//
	private String tp6;//
	private String tp7;//
	private String tp8;//
	//沉淀池SS
	private String stss1;//
	private String stss2;//
	private String stss3;//
	private String stss4;//
	private String stss5;//
	private String stss6;//
	private String stss7;//
	private String stss8;//
	//沉淀池TP
	private String sttp1;//
	private String sttp2;//
	private String sttp3;//
	private String sttp4;//
	private String sttp5;//
	private String sttp6;//
	private String sttp7;//
	private String sttp8;//
//	/颜色
	private String color1;//
	private String color2;//
	private String color3;//
	private String color4;//
	private String color5;//
	private String color6;//
	private String color7;//
	private String color8;//
	//气味
	private String smell1;//
	private String smell2;//
	private String smell3;//
	private String smell4;//
	private String smell5;//
	private String smell6;//
	private String smell7;//
	private String smell8;//
	//设备运行情况
	//提升泵1
	private String pumpFirst1;//
	private String pumpFirst2;//
	private String pumpFirst3;//
	private String pumpFirst4;//
	private String pumpFirst5;//
	private String pumpFirst6;//
	private String pumpFirst7;//
	//提升泵2
	private String pumpSecond1;//
	private String pumpSecond2;//
	private String pumpSecond3;//
	private String pumpSecond4;//
	private String pumpSecond5;//
	private String pumpSecond6;//
	private String pumpSecond7;//
	//提升泵3
	private String pumpThird1;//
	private String pumpThird2;//
	private String pumpThird3;//
	private String pumpThird4;//
	private String pumpThird5;//
	private String pumpThird6;//
	private String pumpThird7;//
	//沉淀池吸/刮泥机
	private String stsos1;//
	private String stsos2;//
	private String stsos3;//
	private String stsos4;//
	private String stsos5;//
	private String stsos6;//
	private String stsos7;//
	//紫外消毒灯
	private String lamp1;//
	private String lamp2;//
	private String lamp3;//
	private String lamp4;//
	private String lamp5;//
	private String lamp6;//
	private String lamp7;//
	//压泥机
	private String pmm1;//
	private String pmm2;//
	private String pmm3;//
	private String pmm4;//
	private String pmm5;//
	private String pmm6;//
	private String pmm7;//
	
	//生化池
	private String sv30;//SV30(%)
	private String sludge;//污泥浓度(g/L)
	private String oxygen;//溶解氧（mg/L）
	private String mixture;//混合液回流比（%）
	private String reflux;//污泥回流比（%）
	
	//快渗池情况
	private String conditionDesc;//养护情况
	//落干时间(min)
	private String lt60;//≤60
	private String g6l12;//60-120
	private String g12l18;//120-180
	private String gt180;//180以上
	
	//设备运行工况
	private String equipment;//机电设备运行维护
	private String instrument;//中控仪表
	
	private String handicraft;//工艺运行情况
	private String problem;//存在问题及需要上级沟通解决问题

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getEarlyWorker() {
		return earlyWorker;
	}

	public void setEarlyWorker(String earlyWorker) {
		this.earlyWorker = earlyWorker;
	}

	public String getMiddleWorker() {
		return middleWorker;
	}

	public void setMiddleWorker(String middleWorker) {
		this.middleWorker = middleWorker;
	}

	public String getLastWorker() {
		return lastWorker;
	}

	public void setLastWorker(String lastWorker) {
		this.lastWorker = lastWorker;
	}

	public String getDwt1() {
		return dwt1;
	}

	public void setDwt1(String dwt1) {
		this.dwt1 = dwt1;
	}

	public String getDwt2() {
		return dwt2;
	}

	public void setDwt2(String dwt2) {
		this.dwt2 = dwt2;
	}

	public String getSq1() {
		return sq1;
	}

	public void setSq1(String sq1) {
		this.sq1 = sq1;
	}

	public String getSq2() {
		return sq2;
	}

	public void setSq2(String sq2) {
		this.sq2 = sq2;
	}

	public String getMeter() {
		return meter;
	}

	public void setMeter(String meter) {
		this.meter = meter;
	}

	public String getElectricity() {
		return electricity;
	}

	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}

	public String getWaterMeter() {
		return waterMeter;
	}

	public void setWaterMeter(String waterMeter) {
		this.waterMeter = waterMeter;
	}

	public String getWater() {
		return water;
	}

	public void setWater(String water) {
		this.water = water;
	}

	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	public String getIpra() {
		return ipra;
	}

	public void setIpra(String ipra) {
		this.ipra = ipra;
	}

	public String getPam1() {
		return pam1;
	}

	public void setPam1(String pam1) {
		this.pam1 = pam1;
	}

	public String getPam2() {
		return pam2;
	}

	public void setPam2(String pam2) {
		this.pam2 = pam2;
	}

	public String getDisin() {
		return disin;
	}

	public void setDisin(String disin) {
		this.disin = disin;
	}

	public String getStopwatertime() {
		return stopwatertime;
	}

	public void setStopwatertime(String stopwatertime) {
		this.stopwatertime = stopwatertime;
	}

	public String getStopreason() {
		return stopreason;
	}

	public void setStopreason(String stopreason) {
		this.stopreason = stopreason;
	}

	public String getRecoverytime() {
		return recoverytime;
	}

	public void setRecoverytime(String recoverytime) {
		this.recoverytime = recoverytime;
	}

	public String getReportDesc() {
		return reportDesc;
	}

	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}

	public String getCod1() {
		return cod1;
	}

	public void setCod1(String cod1) {
		this.cod1 = cod1;
	}

	public String getCod2() {
		return cod2;
	}

	public void setCod2(String cod2) {
		this.cod2 = cod2;
	}

	public String getCod3() {
		return cod3;
	}

	public void setCod3(String cod3) {
		this.cod3 = cod3;
	}

	public String getCod4() {
		return cod4;
	}

	public void setCod4(String cod4) {
		this.cod4 = cod4;
	}

	public String getCod5() {
		return cod5;
	}

	public void setCod5(String cod5) {
		this.cod5 = cod5;
	}

	public String getCod6() {
		return cod6;
	}

	public void setCod6(String cod6) {
		this.cod6 = cod6;
	}

	public String getCod7() {
		return cod7;
	}

	public void setCod7(String cod7) {
		this.cod7 = cod7;
	}

	public String getCod8() {
		return cod8;
	}

	public void setCod8(String cod8) {
		this.cod8 = cod8;
	}

	public String getAn1() {
		return an1;
	}

	public void setAn1(String an1) {
		this.an1 = an1;
	}

	public String getAn2() {
		return an2;
	}

	public void setAn2(String an2) {
		this.an2 = an2;
	}

	public String getAn3() {
		return an3;
	}

	public void setAn3(String an3) {
		this.an3 = an3;
	}

	public String getAn4() {
		return an4;
	}

	public void setAn4(String an4) {
		this.an4 = an4;
	}

	public String getAn5() {
		return an5;
	}

	public void setAn5(String an5) {
		this.an5 = an5;
	}

	public String getAn6() {
		return an6;
	}

	public void setAn6(String an6) {
		this.an6 = an6;
	}

	public String getAn7() {
		return an7;
	}

	public void setAn7(String an7) {
		this.an7 = an7;
	}

	public String getAn8() {
		return an8;
	}

	public void setAn8(String an8) {
		this.an8 = an8;
	}

	public String getTn1() {
		return tn1;
	}

	public void setTn1(String tn1) {
		this.tn1 = tn1;
	}

	public String getTn2() {
		return tn2;
	}

	public void setTn2(String tn2) {
		this.tn2 = tn2;
	}

	public String getTn3() {
		return tn3;
	}

	public void setTn3(String tn3) {
		this.tn3 = tn3;
	}

	public String getTn4() {
		return tn4;
	}

	public void setTn4(String tn4) {
		this.tn4 = tn4;
	}

	public String getTn5() {
		return tn5;
	}

	public void setTn5(String tn5) {
		this.tn5 = tn5;
	}

	public String getTn6() {
		return tn6;
	}

	public void setTn6(String tn6) {
		this.tn6 = tn6;
	}

	public String getTn7() {
		return tn7;
	}

	public void setTn7(String tn7) {
		this.tn7 = tn7;
	}

	public String getTn8() {
		return tn8;
	}

	public void setTn8(String tn8) {
		this.tn8 = tn8;
	}

	public String getTp1() {
		return tp1;
	}

	public void setTp1(String tp1) {
		this.tp1 = tp1;
	}

	public String getTp2() {
		return tp2;
	}

	public void setTp2(String tp2) {
		this.tp2 = tp2;
	}

	public String getTp3() {
		return tp3;
	}

	public void setTp3(String tp3) {
		this.tp3 = tp3;
	}

	public String getTp4() {
		return tp4;
	}

	public void setTp4(String tp4) {
		this.tp4 = tp4;
	}

	public String getTp5() {
		return tp5;
	}

	public void setTp5(String tp5) {
		this.tp5 = tp5;
	}

	public String getTp6() {
		return tp6;
	}

	public void setTp6(String tp6) {
		this.tp6 = tp6;
	}

	public String getTp7() {
		return tp7;
	}

	public void setTp7(String tp7) {
		this.tp7 = tp7;
	}

	public String getTp8() {
		return tp8;
	}

	public void setTp8(String tp8) {
		this.tp8 = tp8;
	}

	public String getStss1() {
		return stss1;
	}

	public void setStss1(String stss1) {
		this.stss1 = stss1;
	}

	public String getStss2() {
		return stss2;
	}

	public void setStss2(String stss2) {
		this.stss2 = stss2;
	}

	public String getStss3() {
		return stss3;
	}

	public void setStss3(String stss3) {
		this.stss3 = stss3;
	}

	public String getStss4() {
		return stss4;
	}

	public void setStss4(String stss4) {
		this.stss4 = stss4;
	}

	public String getStss5() {
		return stss5;
	}

	public void setStss5(String stss5) {
		this.stss5 = stss5;
	}

	public String getStss6() {
		return stss6;
	}

	public void setStss6(String stss6) {
		this.stss6 = stss6;
	}

	public String getStss7() {
		return stss7;
	}

	public void setStss7(String stss7) {
		this.stss7 = stss7;
	}

	public String getStss8() {
		return stss8;
	}

	public void setStss8(String stss8) {
		this.stss8 = stss8;
	}

	public String getSttp1() {
		return sttp1;
	}

	public void setSttp1(String sttp1) {
		this.sttp1 = sttp1;
	}

	public String getSttp2() {
		return sttp2;
	}

	public void setSttp2(String sttp2) {
		this.sttp2 = sttp2;
	}

	public String getSttp3() {
		return sttp3;
	}

	public void setSttp3(String sttp3) {
		this.sttp3 = sttp3;
	}

	public String getSttp4() {
		return sttp4;
	}

	public void setSttp4(String sttp4) {
		this.sttp4 = sttp4;
	}

	public String getSttp5() {
		return sttp5;
	}

	public void setSttp5(String sttp5) {
		this.sttp5 = sttp5;
	}

	public String getSttp6() {
		return sttp6;
	}

	public void setSttp6(String sttp6) {
		this.sttp6 = sttp6;
	}

	public String getSttp7() {
		return sttp7;
	}

	public void setSttp7(String sttp7) {
		this.sttp7 = sttp7;
	}

	public String getSttp8() {
		return sttp8;
	}

	public void setSttp8(String sttp8) {
		this.sttp8 = sttp8;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getColor3() {
		return color3;
	}

	public void setColor3(String color3) {
		this.color3 = color3;
	}

	public String getColor4() {
		return color4;
	}

	public void setColor4(String color4) {
		this.color4 = color4;
	}

	public String getColor5() {
		return color5;
	}

	public void setColor5(String color5) {
		this.color5 = color5;
	}

	public String getColor6() {
		return color6;
	}

	public void setColor6(String color6) {
		this.color6 = color6;
	}

	public String getColor7() {
		return color7;
	}

	public void setColor7(String color7) {
		this.color7 = color7;
	}

	public String getColor8() {
		return color8;
	}

	public void setColor8(String color8) {
		this.color8 = color8;
	}

	public String getSmell1() {
		return smell1;
	}

	public void setSmell1(String smell1) {
		this.smell1 = smell1;
	}

	public String getSmell2() {
		return smell2;
	}

	public void setSmell2(String smell2) {
		this.smell2 = smell2;
	}

	public String getSmell3() {
		return smell3;
	}

	public void setSmell3(String smell3) {
		this.smell3 = smell3;
	}

	public String getSmell4() {
		return smell4;
	}

	public void setSmell4(String smell4) {
		this.smell4 = smell4;
	}

	public String getSmell5() {
		return smell5;
	}

	public void setSmell5(String smell5) {
		this.smell5 = smell5;
	}

	public String getSmell6() {
		return smell6;
	}

	public void setSmell6(String smell6) {
		this.smell6 = smell6;
	}

	public String getSmell7() {
		return smell7;
	}

	public void setSmell7(String smell7) {
		this.smell7 = smell7;
	}

	public String getSmell8() {
		return smell8;
	}

	public void setSmell8(String smell8) {
		this.smell8 = smell8;
	}

	public String getPumpFirst1() {
		return pumpFirst1;
	}

	public void setPumpFirst1(String pumpFirst1) {
		this.pumpFirst1 = pumpFirst1;
	}

	public String getPumpFirst2() {
		return pumpFirst2;
	}

	public void setPumpFirst2(String pumpFirst2) {
		this.pumpFirst2 = pumpFirst2;
	}

	public String getPumpFirst3() {
		return pumpFirst3;
	}

	public void setPumpFirst3(String pumpFirst3) {
		this.pumpFirst3 = pumpFirst3;
	}

	public String getPumpFirst4() {
		return pumpFirst4;
	}

	public void setPumpFirst4(String pumpFirst4) {
		this.pumpFirst4 = pumpFirst4;
	}

	public String getPumpFirst5() {
		return pumpFirst5;
	}

	public void setPumpFirst5(String pumpFirst5) {
		this.pumpFirst5 = pumpFirst5;
	}

	public String getPumpFirst6() {
		return pumpFirst6;
	}

	public void setPumpFirst6(String pumpFirst6) {
		this.pumpFirst6 = pumpFirst6;
	}

	public String getPumpFirst7() {
		return pumpFirst7;
	}

	public void setPumpFirst7(String pumpFirst7) {
		this.pumpFirst7 = pumpFirst7;
	}

	public String getPumpSecond1() {
		return pumpSecond1;
	}

	public void setPumpSecond1(String pumpSecond1) {
		this.pumpSecond1 = pumpSecond1;
	}

	public String getPumpSecond2() {
		return pumpSecond2;
	}

	public void setPumpSecond2(String pumpSecond2) {
		this.pumpSecond2 = pumpSecond2;
	}

	public String getPumpSecond3() {
		return pumpSecond3;
	}

	public void setPumpSecond3(String pumpSecond3) {
		this.pumpSecond3 = pumpSecond3;
	}

	public String getPumpSecond4() {
		return pumpSecond4;
	}

	public void setPumpSecond4(String pumpSecond4) {
		this.pumpSecond4 = pumpSecond4;
	}

	public String getPumpSecond5() {
		return pumpSecond5;
	}

	public void setPumpSecond5(String pumpSecond5) {
		this.pumpSecond5 = pumpSecond5;
	}

	public String getPumpSecond6() {
		return pumpSecond6;
	}

	public void setPumpSecond6(String pumpSecond6) {
		this.pumpSecond6 = pumpSecond6;
	}

	public String getPumpSecond7() {
		return pumpSecond7;
	}

	public void setPumpSecond7(String pumpSecond7) {
		this.pumpSecond7 = pumpSecond7;
	}

	public String getPumpThird1() {
		return pumpThird1;
	}

	public void setPumpThird1(String pumpThird1) {
		this.pumpThird1 = pumpThird1;
	}

	public String getPumpThird2() {
		return pumpThird2;
	}

	public void setPumpThird2(String pumpThird2) {
		this.pumpThird2 = pumpThird2;
	}

	public String getPumpThird3() {
		return pumpThird3;
	}

	public void setPumpThird3(String pumpThird3) {
		this.pumpThird3 = pumpThird3;
	}

	public String getPumpThird4() {
		return pumpThird4;
	}

	public void setPumpThird4(String pumpThird4) {
		this.pumpThird4 = pumpThird4;
	}

	public String getPumpThird5() {
		return pumpThird5;
	}

	public void setPumpThird5(String pumpThird5) {
		this.pumpThird5 = pumpThird5;
	}

	public String getPumpThird6() {
		return pumpThird6;
	}

	public void setPumpThird6(String pumpThird6) {
		this.pumpThird6 = pumpThird6;
	}

	public String getPumpThird7() {
		return pumpThird7;
	}

	public void setPumpThird7(String pumpThird7) {
		this.pumpThird7 = pumpThird7;
	}

	public String getStsos1() {
		return stsos1;
	}

	public void setStsos1(String stsos1) {
		this.stsos1 = stsos1;
	}

	public String getStsos2() {
		return stsos2;
	}

	public void setStsos2(String stsos2) {
		this.stsos2 = stsos2;
	}

	public String getStsos3() {
		return stsos3;
	}

	public void setStsos3(String stsos3) {
		this.stsos3 = stsos3;
	}

	public String getStsos4() {
		return stsos4;
	}

	public void setStsos4(String stsos4) {
		this.stsos4 = stsos4;
	}

	public String getStsos5() {
		return stsos5;
	}

	public void setStsos5(String stsos5) {
		this.stsos5 = stsos5;
	}

	public String getStsos6() {
		return stsos6;
	}

	public void setStsos6(String stsos6) {
		this.stsos6 = stsos6;
	}

	public String getStsos7() {
		return stsos7;
	}

	public void setStsos7(String stsos7) {
		this.stsos7 = stsos7;
	}

	public String getLamp1() {
		return lamp1;
	}

	public void setLamp1(String lamp1) {
		this.lamp1 = lamp1;
	}

	public String getLamp2() {
		return lamp2;
	}

	public void setLamp2(String lamp2) {
		this.lamp2 = lamp2;
	}

	public String getLamp3() {
		return lamp3;
	}

	public void setLamp3(String lamp3) {
		this.lamp3 = lamp3;
	}

	public String getLamp4() {
		return lamp4;
	}

	public void setLamp4(String lamp4) {
		this.lamp4 = lamp4;
	}

	public String getLamp5() {
		return lamp5;
	}

	public void setLamp5(String lamp5) {
		this.lamp5 = lamp5;
	}

	public String getLamp6() {
		return lamp6;
	}

	public void setLamp6(String lamp6) {
		this.lamp6 = lamp6;
	}

	public String getLamp7() {
		return lamp7;
	}

	public void setLamp7(String lamp7) {
		this.lamp7 = lamp7;
	}

	public String getPmm1() {
		return pmm1;
	}

	public void setPmm1(String pmm1) {
		this.pmm1 = pmm1;
	}

	public String getPmm2() {
		return pmm2;
	}

	public void setPmm2(String pmm2) {
		this.pmm2 = pmm2;
	}

	public String getPmm3() {
		return pmm3;
	}

	public void setPmm3(String pmm3) {
		this.pmm3 = pmm3;
	}

	public String getPmm4() {
		return pmm4;
	}

	public void setPmm4(String pmm4) {
		this.pmm4 = pmm4;
	}

	public String getPmm5() {
		return pmm5;
	}

	public void setPmm5(String pmm5) {
		this.pmm5 = pmm5;
	}

	public String getPmm6() {
		return pmm6;
	}

	public void setPmm6(String pmm6) {
		this.pmm6 = pmm6;
	}

	public String getPmm7() {
		return pmm7;
	}

	public void setPmm7(String pmm7) {
		this.pmm7 = pmm7;
	}

	public String getSv30() {
		return sv30;
	}

	public void setSv30(String sv30) {
		this.sv30 = sv30;
	}

	public String getSludge() {
		return sludge;
	}

	public void setSludge(String sludge) {
		this.sludge = sludge;
	}

	public String getOxygen() {
		return oxygen;
	}

	public void setOxygen(String oxygen) {
		this.oxygen = oxygen;
	}

	public String getMixture() {
		return mixture;
	}

	public void setMixture(String mixture) {
		this.mixture = mixture;
	}

	public String getReflux() {
		return reflux;
	}

	public void setReflux(String reflux) {
		this.reflux = reflux;
	}

	public String getConditionDesc() {
		return conditionDesc;
	}

	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}

	public String getLt60() {
		return lt60;
	}

	public void setLt60(String lt60) {
		this.lt60 = lt60;
	}

	public String getG6l12() {
		return g6l12;
	}

	public void setG6l12(String g6l12) {
		this.g6l12 = g6l12;
	}

	public String getG12l18() {
		return g12l18;
	}

	public void setG12l18(String g12l18) {
		this.g12l18 = g12l18;
	}

	public String getGt180() {
		return gt180;
	}

	public void setGt180(String gt180) {
		this.gt180 = gt180;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getHandicraft() {
		return handicraft;
	}

	public void setHandicraft(String handicraft) {
		this.handicraft = handicraft;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}


}
