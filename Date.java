
public class Date implements Comparable<Date> {
	 public enum Month

     {

           JANUARY,

           FEBRUARY,

           MARCH,

           APRIL,
           
           MAY,
           
           JUNE,
           
           JULY,
           
           AUGUEST,
           
           SEPTEMBER,
           
           OCTORBER,
           
           NOVEMBER,           
           
           DECEMBER

     }    

     public Date(){
    	 
     };
     
     public Date(int day, int month, int year){
    	 date[0] = day;
    	 date[1] = month;
    	 date[2] = year;
     }
     
     public static void main(String[] args){
    	 Date test = new Date(20, Month.APRIL, 1957);
     }

     public Date( int day, Month month, int year){
    	 //test Date valid
    	if(!IsValid(day, month, year)){
    		System.out.println("Wrong format!! Try again!");
    	}else{
    		//Valid, initial global variable date
    		date[0] = day;
    		//use changeMon method, Month->Integer
	       	 date[1] = changeMon(month);
	       	 date[2] = year;
    	}
//    	 switch(month){
//    	 case JANUARY: NumMon = 1; break;
//    	 case FEBRUARY: NumMon = 2; break;
//    	 case MARCH: NumMon = 3; break;
//    	 case APRIL: NumMon = 4; break;
//    	 case MAY: NumMon = 5; break;
//    	 case JUNE: NumMon = 6; break;
//    	 case JULY: NumMon = 7; break;
//    	 case AUGUEST: NumMon = 8; break;
//    	 case SEPTEMBER: NumMon = 9; break;
//    	 case OCTORBER: NumMon = 10; break;
//    	 case NOVEMBER: NumMon = 11; break;
//    	 case DECEMBER: NumMon = 12; break;
//    	 } 
     };
     //Two global variables
     private int mJulianNumber;    // Julian Day Number
     private int[] date = new int[3];

     public static Boolean IsLeapYear( int year ){
    	 if(year % 4 != 0){
    		 return false;
    	 }else if(year % 100 != 0){
    		 return true;
    	 }else if(year % 400 != 0){
    		 return false;
    	 }
    	 return true;
     };

     public static Boolean IsValid( int day, Month month, int year ){
    	 //day range: 1-31
    	 if(day <= 0 || day >31)
    		 	return false;
    	 //Leap year or not
    	 boolean leap = IsLeapYear(year);
    	 //Integer month
    	 int numMon = 0;
    		numMon	= changeMon(month);
    	//check Feb.	
    	 if(numMon == 2){
    		 if(leap && day > 29){
    			 return false;
    		 }else if(!leap && day > 28){
    			 return false;
    		 }
    		 return true;
    	 }
    	 //Initial days in different months
    	 int[] monList = {1,3,4,5,6,7,8,9,10,11,12};
    	 int[] dayList = {31,31,30,31,30,31,31,30,31,30,31};
    	 for(int i = 0; i < monList.length; i++){
    		 if(numMon == monList[i] && day <= dayList[i]){
    				 return true;
    		 }
    	 }
    	 return false;
     };

     public int GetDay(){
    	 return date[0];
     };

     public Month GetMonth(){
    	 return changeMon(date[1]);
     };

     public int GetYear(){
    	 return date[2];
     };

    public int compareTo(Date dateTo){
    	//Calculate Julian number of this object
    	this.ToJulianNumber(this.date[0], this.date[1], this.date[2]);
    	int thisJulian = this.mJulianNumber;
    	//Calculate Julian number of compared object
    	dateTo.ToJulianNumber(dateTo.date[0], dateTo.date[1], dateTo.date[2]);
    	int compareJulian = dateTo.mJulianNumber;
    	//return the difference of Julian number
    	return thisJulian - compareJulian;
    };

    public static int changeMon(Month month){
    	//change from Month to Integer month
    	for(int i = 0; i < Month.values().length; i++){
   		 if(month == Month.values()[i]){
   			return i+1;
   		 }
   	 	}
    	return 0;
    }
    public static Month changeMon(int num){
    	//change from Integer month to Month
    	return Month.values()[num-1];
    }

     private void ToJulianNumber(int day, int month, int year){
    	 mJulianNumber = ( 1461 * (year + 4800 + (month - 14 ) / 12 ) ) / 4 +

				 ( 367 * (month - 2 - 12 * ( (month - 14 ) / 12 ) ) ) / 12 -

				 ( 3 * ( ( year + 4900 + ( month - 14 ) / 12 ) / 100 ) ) / 4 +

				 day - 32075; 
     };

     private void FromJulianNumber(int[] dateTo ){
    	 int l = mJulianNumber + 68569;

		 int n = ( 4 * l ) / 146097;

		 l = l - ( 146097 * n + 3 ) / 4;

		 int i = ( 4000 * ( l + 1 ) ) / 1461001;

		 l = l - ( 1461 * i ) / 4 + 31;

		 int j = ( 80 * l ) / 2447;

		 int day = l - ( 2447 * j ) / 80;

		 l = j / 11;

		 int month = j + 2 - ( 12 * l );

		 int year = 100 * ( n - 49 ) + i + l;
		 dateTo[0] = day;
		 dateTo[1] = month;
		 dateTo[2] = year;
     };

    

     
}
