package hello.value.weather;

public class MeteoLocal {
 String firstcity;
 String secondcity;
 Double firsttime;
 Double secondtime;
 public MeteoLocal() {
	 
 }
public MeteoLocal(String firstcity, String secondcity, Double firsttime, Double secondtime) {
	super();
	this.firstcity = firstcity;
	this.secondcity = secondcity;
	this.firsttime = firsttime;
	this.secondtime = secondtime;
}
public String getFirstcity() {
	return firstcity;
}
public void setFirstcity(String firstcity) {
	this.firstcity = firstcity;
}
public String getSecondcity() {
	return secondcity;
}
public void setSecondcity(String secondcity) {
	this.secondcity = secondcity;
}
public Double getFirsttime() {
	return firsttime;
}
public void setFirsttime(Double firsttime) {
	this.firsttime = firsttime;
}
public Double getSecondtime() {
	return secondtime;
}
public void setSecondtime(Double secondtime) {
	this.secondtime = secondtime;
}

}