var PATTERN_CPF = "\d{3}\.\d{3}\.\d{3}-\d{2}"; //XXX.XXX.XXX-XX
var PATTERN_PHONE = "\([0-9]{2}\)[\s][0-9]{4}-[0-9]{4}"; //(XX)XXXX-XXXX
var PATTERN_CEP = "[0-9]{5}-[0-9]{3}"; //XXXXX-XXX
var PATTERN_EMAIL = "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";

//Must contain at least one number and 
//one uppercase and lowercase letter, and 
//at least 8 or more characters
var PATTERN_PASSWD = "(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
var PATTERN_COUNTRY_CODE = "[A-Za-z]{3}";
var PATTERN_URL = "https?://.+";
var PATTERN_DATE = "\d{1,2}/\d{1,2}/\d{4}"; // DD/MM/YYYY ou  MM/DD/YYYY
var PATTERN_MONEY = "[0-9]{1,3}\.?[0-9]{1,3},[0-9]{2}$"; //[0-9],[0-9][0-9]
// [0-9]+([,][0-9]{1,2})?
var PATTERN_TIME = "[0-9]{2}:[0-9]{2}$";
var PATTERN_ONLY_LETTERS = "[a-z\s]+$";
var PATTER_ONLY_NUMBERS = "[0-9]+$";