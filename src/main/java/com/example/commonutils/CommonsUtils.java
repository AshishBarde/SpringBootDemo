package com.example.commonutils;
package com.hovs.benefit.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hovs.benefit.common.enumration.Constants;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;

public final class CommonUtils {
	
	private static Logger logger = LoggerFactory.getLogger("CommonUtils");
	
	public static DateFormat timeStampformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static DateFormat formatYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	
	public static void encryptAndDecryptFields(TextEncryptor textEncryptor,Object object,boolean encryptFlag,String... fieldsList){
		
		Field[] fields = object.getClass().getDeclaredFields();
		Map<String,Field> fieldMap = new HashMap<String,Field>();
		for(Field field : fields){
			fieldMap.put(field.getName(),field);
		}
		
		for(String varibleName : fieldsList){
		   if(fieldMap.containsKey(varibleName)){
			   fieldMap.get(varibleName).setAccessible(true);
			   try {
				   if (encryptFlag){
				      fieldMap.get(varibleName).set(object,fieldMap.get(varibleName).get(object) != null && !fieldMap.get(varibleName).get(object).toString().isEmpty() ? textEncryptor.encrypt(fieldMap.get(varibleName).get(object).toString()) : "");
				   }else{
					   fieldMap.get(varibleName).set(object,fieldMap.get(varibleName).get(object) != null && !fieldMap.get(varibleName).get(object).toString().isEmpty() ? textEncryptor.decrypt(fieldMap.get(varibleName).get(object).toString()) : "");
				   }
			} catch (Exception e) {
				logger.error("Exception occured while encrypt And Decrypting Fields "+e.getMessage());
				//e.printStackTrace();
			}
		   }
		}	
	}
	
	public static void updateFields(Object object,List<String> fieldsList){
		
		Field[] fields = object.getClass().getDeclaredFields();
		Map<String,Field> fieldMap = new HashMap<String,Field>();
		for(Field field : fields){
			fieldMap.put(field.getName(),field);
		}
		
		for(String varibleName : fieldsList){
		   if(fieldMap.containsKey(varibleName)){
			   fieldMap.get(varibleName).setAccessible(true);
			   try {
				   
				   
			} catch (Exception e) {
				logger.error("Exception occured while encrypt And Decrypting Fields "+e.getMessage());
				//e.printStackTrace();
			}
		   }
		}	
	}
	
	public static String getOnlyNumbersFromString(String string){
		if(string!=null && !string.isEmpty()){
			return string.replaceAll("[^0-9]","");
		}
		return string;
	}
	
	public static void maskingFields(Object object, int visibleLength, String... fieldsList){
		
		Field[] fields = object.getClass().getDeclaredFields();
		Map<String,Field> fieldMap = new HashMap<String,Field>();
		for(Field field : fields){
			fieldMap.put(field.getName(),field);
		}
		for(String varibleName : fieldsList){
			   if(fieldMap.containsKey(varibleName)){
				   fieldMap.get(varibleName).setAccessible(true);
				   try {
					     String fieldValue =  fieldMap.get(varibleName).get(object).toString();
					     if(fieldValue == null){
					    	  continue;
					     }else{
					    	 if(!fieldValue.isEmpty() ){
					    		 //char[] chars = fieldValue.toCharArray();
					    		 char[] chars = CommonUtils.getOnlyNumbersFromString(fieldValue).toCharArray();
					    		 if(fieldValue.length()>visibleLength) {
					    			 Arrays.fill(chars, 0, chars.length - visibleLength, 'X');
							    	 fieldMap.get(varibleName).set(object,new String(chars));
					    		 }else{
					    			 Arrays.fill(chars, 0, chars.length - 1, 'X');
							    	 fieldMap.get(varibleName).set(object,new String(chars));
					    		 }
					    	 }/*else if(!fieldValue.isEmpty() && fieldValue.length() < visibleLength){
					    		 char[] chars = CommonUtils.getOnlyNumbersFromString(fieldValue).toCharArray();
					    	 }*/
					     }
				} catch (Exception e) {
					logger.error("Exception occured while masking Fields "+e.getMessage());
					//e.printStackTrace();
				}
			   }
			} 

	}
	
	public static String formatDateYYYYMMDD(String date) {
		if (date != null && !date.isEmpty() && date.contains(":")) {
			try {
				return new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(date));
			} catch (Exception exception) {
				logger.error("Exception occured while formatting date in YYYYMMDD format "+exception.getMessage());
				//exception.printStackTrace();
			}
		}
		return date;
	}
	
	public static String formatPhoneNumber(String phone){
		try {
			if(phone!=null&&!phone.isEmpty()&&phone.contains("-")){
				if(phone.contains("(")&&phone.contains(")")){
					return phone.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
				}
				else{
					return phone.replace("-", "");
				}
			}
		} catch (Exception e) {
			logger.error("Exception occured while formatting phone number "+e.getMessage());
			//e.printStackTrace();
		}
		return phone;
	}
	
	public static String removeSpaces(String str){
		try{
			if(str!=null && !str.isEmpty()){
				return str.replaceAll(Constants.ConstantFlags.REMOVESPACES.getFlagValue(), "");
			}
		} catch (Exception e) {
			logger.error("Exception occured while removing Spaces"+e.getMessage());
			//e.printStackTrace();
		}	
		return str;
	}
	
	public static String formatZipcode(String zipCode){
		try{
			if(zipCode!=null && !zipCode.isEmpty() && zipCode.length()<5){
				return ("00000".substring(0,5-zipCode.length())+zipCode);
			}else if(zipCode!=null && !zipCode.isEmpty() && zipCode.length()>5 && zipCode.length()<9){
				return ("000000000".substring(0,9-zipCode.length())+zipCode); 
			}
		} catch (Exception e) {
			logger.error("Exception occured while formatting zip code "+e.getMessage());
			//e.printStackTrace();
		}	
		return zipCode;
	}
	
	public static String formatFax(String fax){
		try{
			if(fax!=null && fax.contains(" ")){
				return fax.replace(" ", "");
			}
		} catch (Exception e) {
			logger.error("Exception occured while formatting fax "+e.getMessage());
			//e.printStackTrace();
		}	
		return fax;
	}
	public static String formatSsn(String ssn){
		try{
			if(ssn!=null && !ssn.isEmpty() && ssn.contains("-")){
				return ssn.replace("-", "");
			}else if(ssn!=null && !ssn.isEmpty() && ssn.contains("")){
				return ssn.replace(" ", "");
			}else if(ssn!=null && !ssn.isEmpty() && ssn.contains("(")){
				return ssn.replace("(", "");
			}
			else if(ssn!=null && !ssn.isEmpty() && ssn.contains(")")){
				return ssn.replace(")", "");
			}
		} catch (Exception e) {
			logger.error("Exception occured while formatting ssn "+e.getMessage());
			//e.printStackTrace();
		}	
		return ssn;
	}
	
	public static String formatDateFields(String inwardEmp) throws ParseException {
		if(inwardEmp!=null && !inwardEmp.isEmpty() && inwardEmp.contains("-")){
		return new SimpleDateFormat(
				"yyyy/MM/dd").format(new SimpleDateFormat(
				"yyyy-MM-dd").parse(inwardEmp));
		}else if(inwardEmp!=null && !inwardEmp.isEmpty()){
			return new SimpleDateFormat(
					"yyyy/MM/dd").format(new SimpleDateFormat(
					"yyyy/MM/dd").parse(inwardEmp));
		}
		return inwardEmp;
	}
	
	public static String formatDates(String date) throws ParseException {
		try {
			if(date!=null && !date.isEmpty() && date.contains("/") ){
	
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").
						format(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(date));
				
			}else if(date!=null && !date.isEmpty() && date.contains("-")){
				
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").
						format(new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").parse(date));
			}
		} catch (Exception e) {
			logger.error("Exception occured in formatDates "+e.getMessage());
		}
		return date;
	}
	
	public static String replaceAllSpecialChars(String string){
		if(string!=null && !string.isEmpty()){
			//return string.replaceAll("[^\\w\\s]","");
			return string.replaceAll("[^\\w]","");
		}
		return string;
	}
	
	public static String replaceAllSpecialCharsWithSpaces(String string){
		if(string!=null && !string.isEmpty()){
			return string.replaceAll("[^\\w\\s]","").replaceAll("\\s", "");
			//return string.replaceAll("[^\\w]","");
		}
		return string;
	}

	public static String removeHyphen(String str){
		try{
			if(str!=null && str.contains("-")){
				return str.replace("-", "");
			}
		} catch (Exception e) {
			logger.error("Exception occured while removing hyphen "+e.getMessage());
			//e.printStackTrace();
		}	
		return str;
	}
	public static String formatTerminationDateYYYYMMDD(String date) {
		if (date != null && !date.isEmpty()) {
			try {
				return new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(date));
			} catch (Exception exception) {
				logger.error("Exception occured while formatting TerminationDate in YYYYMMDD format "+exception.getMessage());
				//exception.printStackTrace();
			}
		}
		return date;
	}
	
	/*public static Double convertToDoubleValue(Double obj){
		if(obj!=null){
			try {
				Double value = (obj==null ? 0.00 : obj); 
				
				Double d = Math.round( Double.parseDouble(value.toString()) * 100.0 ) / 100.0;
				return d;
				
			} catch (Exception exception) {
				logger.error("Exception occured while converting string To Double Value "+exception.getMessage());
				exception.printStackTrace();
			}
		}
		return obj;
	}*/
	
	public static String convertToDoubleValue(String obj){
		if(obj!=null && !obj.isEmpty()){
			try {
				BigDecimal bigDecimal = new BigDecimal(obj);
		        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		        return bigDecimal+"";
				/*Double d = Math.round( Double.parseDouble(value.toString()) * 100.0 ) / 100.0;
				return d.toString();*/
				
			} catch (Exception exception) {
				logger.error("Exception occured while converting string To Double Value "+exception.getMessage());
				//exception.printStackTrace();
			}
		}
		return obj;
	}
	
	public static String removeDecimalFromDouble(String string){
		if(string!=null && !string.isEmpty()){
			try {
				DecimalFormat df =new DecimalFormat("###.##");
				return df.format(Double.parseDouble(string));
			} catch (Exception exception) {
				logger.error("Exception occured while removing Decimal From Double "+exception.getMessage());
				//exception.printStackTrace();
			}
		}
		return string;
	}
	
	public static String calculatePremiumAmount(String premiumAmount, String payrollFrequency ) {
		if(premiumAmount!=null && payrollFrequency!=null){
			/*if(payrollFrequency.equals("26")){
				Double grossSal = ((Double.parseDouble(premiumAmount))*12)/26;
				return grossSal.toString();
			}*/if(payrollFrequency.equals("52")){
				Double grossSal = ((Double.parseDouble(premiumAmount))*26)/12;
				return grossSal.toString();
			}/*else if(payrollFrequency.equals("12")){
				Double grossSal = ((Double.parseDouble(premiumAmount)))/12;
				return grossSal.toString();
			}*/else{
				return premiumAmount;
			}
		}
		return null;
	}
	
	public static void setDefaultValueToFields(Object object) {
		try {
			for (Field f : object.getClass().getDeclaredFields()) {
				f.setAccessible(true);

				if (f.get(object) == null) {
					if (f.getType().toString().equalsIgnoreCase("class java.lang.String")) {
						f.set(object, "");
					}
					if (f.getType().toString().equalsIgnoreCase("class java.lang.Integer")) {
						f.set(object, new Integer(0));
					}
					if (f.getType().toString().equalsIgnoreCase("class java.lang.Double")) {
						f.set(object, new Double(0.00));
					}
				}else{
					if (f.getType().toString().equalsIgnoreCase("class java.lang.String")) {
						f.set(object,f.get(object).toString().toUpperCase());
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception occured while setting default values to field "+e.getMessage());
			//e.printStackTrace();
		}
	}
	
	public static String calculateLastMonthOfday(String terminationDate,int year) {
		if(terminationDate!=null && !terminationDate.isEmpty()){
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			try {
				Date date = format.parse(terminationDate);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+1);
				if(year>0)
				{
					c.add(Calendar.YEAR, +(26));
				}
				Date stopDate = c.getTime();             
				String BenefitStopDate = format.format(stopDate); 
				return BenefitStopDate;
			} catch (Exception exception) {
				logger.error("Exception occured while calculating Last day Of Month "+exception.getMessage());
				//exception.printStackTrace();
				return null;
			}
		}
		return terminationDate;
	}
	
	public static String calculate60PerOfSalary(String salary) {
		if(salary!=null && !salary.isEmpty()){
			
			try {
				Double value = Double.parseDouble(salary); 
				Double d = Math.round(value*60) / 100.0;
				return d.toString();
			} catch (Exception exception) {
				logger.error("Exception occured while calculating Last day Of Month "+exception.getMessage());
				//exception.printStackTrace();
				return null;
			}
		}
		return salary;
	}
	
	public static ZonedDateTime getFirstDayOfCurrentWeek(ZonedDateTime date) {
		return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	}
	public static ZonedDateTime getLastDayOfCurrentWeek(ZonedDateTime date) {
		return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
	}
	
	public static ZonedDateTime getFirstDayOfWeek(ZonedDateTime date) {
		return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
	}
	public static ZonedDateTime getLastDayOfWeek(ZonedDateTime date) {
		return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
	}
	public static ZonedDateTime getLastDayOfPreviousWeek(ZonedDateTime date) {
		return date.withHour(23).withMinute(59).withSecond(59).minusWeeks(1).with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
	}
	public static ZonedDateTime getFirstDayOfPreviousWeek(ZonedDateTime date) {
		return date.withHour(00).withMinute(00).withSecond(01).minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	}
	public static ZonedDateTime getLastDayOfNextWeek(ZonedDateTime date) {
		return date.withHour(23).withMinute(59).withSecond(59).plusWeeks(1).with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
	}
	public static ZonedDateTime getFirstDayOfNextWeek(ZonedDateTime date) {
		return date.withHour(00).withMinute(00).withSecond(01).plusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	}
	public static ZonedDateTime getLastDayOfCurrentYear(ZonedDateTime date) {
		return date.withHour(23).withMinute(59).withSecond(59).with(TemporalAdjusters.lastDayOfYear());
	}
	public static ZonedDateTime getFirstDayOfCurrentYear(ZonedDateTime date) {
		return date.withHour(00).withMinute(00).withSecond(01).with(TemporalAdjusters.firstDayOfYear());
	}
	public static ZonedDateTime getFirstDayOfPreviousYear(ZonedDateTime date) {
		return date.minusYears(1).withHour(00).withMinute(00).withSecond(01).with(TemporalAdjusters.firstDayOfYear());
	}
	public static ZonedDateTime getLastDayOfPreviousYear(ZonedDateTime date) {
		return date.minusYears(1).withHour(23).withMinute(59).withSecond(59).with(TemporalAdjusters.lastDayOfYear());
	}
	public static ZonedDateTime getLastDayOfMonth(ZonedDateTime date) {
		return date.withHour(23).withMinute(59).withSecond(59).with(TemporalAdjusters.lastDayOfMonth());
	}
	public static ZonedDateTime getFirstDayOfNextMonth(ZonedDateTime date) {
		return date.with(TemporalAdjusters.lastDayOfMonth()).plusMonths(1)
				.withHour(00).withMinute(00).withSecond(01).with(TemporalAdjusters.firstDayOfMonth());
	}
	
	public static String getFirstDayOfNextYear()
	{
		ZonedDateTime currentDate = ZonedDateTime.now();
		ZonedDateTime firstMonthOfNextYear = currentDate
				.plusYears(1).minusMonths(
						currentDate.plusYears(1)
								.getMonthValue() - 1);
		return Timestamp.valueOf(
				firstMonthOfNextYear
						.with(TemporalAdjusters
								.firstDayOfMonth())
						.toLocalDateTime()).toString();
	}
	
	public static String getLastDayOfCurrentMonth()
	{
		 ZonedDateTime currentDate = ZonedDateTime.now();
	  	
	  		return Timestamp.valueOf(
	  				currentDate
						.with(TemporalAdjusters
								.lastDayOfMonth())
						.toLocalDateTime()).toString(); 
	}
	
	public static String getLastDayOfTerminatedEployee(ZonedDateTime terminationDate)
	{
		return Timestamp.valueOf(
				terminationDate
					.with(TemporalAdjusters
							.lastDayOfMonth())
					.toLocalDateTime()).toString(); 
	}
	
	public static String getFirstDayOfNextMonth()
	{
		 ZonedDateTime currentDate = ZonedDateTime.now();
	  	
	  		return Timestamp.valueOf(
	  				currentDate
						.with(TemporalAdjusters
								.firstDayOfNextMonth())
						.toLocalDateTime()).toString(); 
	}
	
	public static String getFirstDayOfCurrentYear()
	{
		ZonedDateTime currentDate = ZonedDateTime.now();
		ZonedDateTime firstMonthOfNextYear = currentDate
				.plusYears(0).minusMonths(
						currentDate.plusYears(1)
								.getMonthValue() - 1);
		return Timestamp.valueOf(
				firstMonthOfNextYear
						.with(TemporalAdjusters
								.firstDayOfMonth())
						.toLocalDateTime()).toString();
	}
	
	public static boolean isLastDayOfMonth(ZonedDateTime date) {
		ZonedDateTime lastDayOfMonth = date.with(TemporalAdjusters
				.lastDayOfMonth());
		if (lastDayOfMonth.compareTo(date) == 0) {
			return true;
		}
		return false;
	}

	public static boolean isFirstDayOfMonth(ZonedDateTime date) {
		ZonedDateTime firstDayOfMonth = date.with(TemporalAdjusters
				.firstDayOfMonth());
		if (firstDayOfMonth.compareTo(date) == 0) {
			return true;
		}
		return false;
	}
	
	public static Boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	public static String createPassword(String ssn, String Dob) {
		ssn = ssn.substring(ssn.length() - 4, ssn.length());// 999978001
		if (Dob.indexOf('/') != -1) {
			Dob = Dob.substring(2, Dob.indexOf('/'));
		} else if (Dob.indexOf('-') != -1) {
			Dob = Dob.substring(2, Dob.indexOf('-'));
		}
		return ssn + Dob;
	}
	
	public static boolean storeFilesOnFTP(List<String> filePaths, String employerName,TextEncryptor textEncryptor,String ftpUserName,String ftpHostName,String ftpPassword,String ftpStorageLocation ) {
		JSch jSch = new JSch();
		Session session = null;
		FileInputStream fileInputStream = null;
		ChannelSftp sftpChannel = null;
		Channel channel = null;
		try {
			System.out.println("********");
			session = jSch.getSession(textEncryptor.decrypt(ftpUserName), ftpHostName);
			//session = jSch.getSession("bancmate", "ec2-23-21-34-77.compute-1.amazonaws.com");
			session.setPort(22);
			session.setPassword(textEncryptor.decrypt(ftpPassword));
			//session.setPassword("LUOABkaNSTfL");
			Properties obj_Properties = new Properties();
			obj_Properties.put("StrictHostKeyChecking", "no");
			session.setConfig(obj_Properties);

			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			System.out.println(ftpStorageLocation!=null ? "ftpStorageLocation ****** : "+ftpStorageLocation : "ftpStorageLocation is null");
			//sftpChannel.cd(ftpStorageLocation);
		
			/*String folderName = nestedFolder(employerName, sftpChannel, ftpStorageLocation, true);
			folderName = nestedFolder(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)), sftpChannel, folderName, true);
			folderName = nestedFolder(Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), sftpChannel, folderName, true);
			folderName = nestedFolder(Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) + " Week", sftpChannel, folderName, true);
			folderName = nestedFolder(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + " Day", sftpChannel, folderName, true);
			nestedFolder("Done", sftpChannel, folderName, false);
			nestedFolder("Ack", sftpChannel, folderName, false);
			nestedFolder("Ready", sftpChannel, folderName, true);*/

			for (String filePath : filePaths) {
				File f = new File(filePath);
				fileInputStream = new FileInputStream(f);
				//sftpChannel.put(fileInputStream, f.getName());
				//sftpChannel.put(filePath, folderName);
				sftpChannel.put(filePath, ftpStorageLocation);
				System.out.println("File copied on SFTP setver ***********");
				/*if(f.delete()){
					System.out.println("file delete : "+filePath);
				}*/
			}
			
			/*for (Map.Entry<String,String> filePath : filePaths.entrySet()) {
				sftpChannel.put(filePath.getValue(), filePath.getKey());
			}*/
			
		} catch (Exception e) {
			logger.error("Exception occured while storing files on SFTP location "+e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				logger.error("Exception occured while storing files on SFTP location "+e.getMessage());
				//e.printStackTrace();
			}
			sftpChannel.exit();
			channel.disconnect();
			session.disconnect();
		}
		return true;
	}
	
	public static String nestedFolder(String folderName, ChannelSftp channelSftp, String baseDir, boolean flag) {
		SftpATTRS attrs = null;
		try {
			try {
				attrs = channelSftp.stat(baseDir + "/" + folderName);
			} catch (Exception exception) {
				System.out.println(baseDir + "/" + folderName + " not found");
			}
			if (attrs == null) {
				System.out.println("Creating dir " + folderName);
				channelSftp.mkdir(folderName);
			}
			if (flag)
				channelSftp.cd(baseDir + "/" + folderName);

			return baseDir + "/" + folderName;
		} catch (Exception exception) {
			logger.error("Exception occured while creating nested Folder "+exception.getMessage());
			//exception.printStackTrace();
			return null;
		}
	}
	
	public static String generateZipFile(List<String> filesList, String employerName,String accessKey, String secreteKey, String region, String bucketName, String sourcePath, String destinationPath) {
		AmazonS3 s3client = getAmazonS3Object(accessKey, secreteKey, region, bucketName, sourcePath, destinationPath);
		
		destinationPath = createDirectoryOnS3Bucket(s3client,bucketName,destinationPath); // Create directory to upload file on S3 bucket
		
		String zipFile = sourcePath + employerName + ".zip";
		FileOutputStream fileOutputStrem = null;
		ZipOutputStream zipOutStream = null;
		FileInputStream fileInputStream = null;
		byte[] buffer = new byte[1024];
		try {
			fileOutputStrem = new FileOutputStream(zipFile);
			zipOutStream = new ZipOutputStream(fileOutputStrem);
			for (String fileName : filesList) {
				String fName="";
				fileInputStream = new FileInputStream(fileName);
				zipOutStream.putNextEntry(new java.util.zip.ZipEntry(fileName));
				int length;

				while ((length = fileInputStream.read(buffer)) > 0) {
					zipOutStream.write(buffer, 0, length);
				}
				zipOutStream.closeEntry();
				fileInputStream.close();
				
				fName = fileName.substring(fileName.lastIndexOf("/")+1, fileName.length());
				String destPath = destinationPath + fName;					
				s3client.putObject(bucketName, destPath, new File(fileName)); // Upload XML file on S3 bucket
				System.out.println(fName + " file is uploaded on S3 bucket...");
				
			}
		} catch (Exception e) {
			logger.error("Exception occured while generating Zip File "+e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				zipOutStream.close();
				fileOutputStrem.close();
				filesList.forEach(file->{
					File f=new File(file);
					f.delete();
				});
			} catch (IOException e) {
				logger.error("Exception occured while generating Zip File "+e.getMessage());
				//e.printStackTrace();
			}

		}
		return zipFile;
	}
	
	public static String createDirectoryOnS3Bucket(AmazonS3 s3client, String bucketName, String destinationPath){
		destinationPath = destinationPath+"/"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"/"+
		Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+"/"+
		"Week"+Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);		
		createFolder(bucketName,destinationPath+"/Ready/",s3client);
		createFolder(bucketName,destinationPath+"/Ack/",s3client);
		createFolder(bucketName,destinationPath+"/Done/",s3client);
		createFolder(bucketName,destinationPath+"/Ready/ADP/",s3client);
		destinationPath = destinationPath+"/Raw/";
		return destinationPath;
	}
	
	public static Date convertStringToDate(String strDateVal){
		Date date=null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US).parse(strDateVal);		    
		} catch (ParseException e) {
			logger.error("Exception occured in convertStringToDate "+e.getMessage());
			//e.printStackTrace();
		} 
		
		return date;
	}

	
    public static Date convertStringToDate2(String strDateVal){
        Date date=null;
        try {
              String year=strDateVal.substring(0,4);  
              String month=strDateVal.substring(4,6);
              String day=strDateVal.substring(6,8);
              String date1=year+"-"+month+"-"+day;
            date=new SimpleDateFormat("yyyy-MM-dd").parse(date1);             
        } catch (ParseException e) {
        	logger.error("Exception occured in convertStringToDate2 "+e.getMessage());
              //e.printStackTrace();
        } 
        
        
        return date;
  }
	
	public static String convertDateFormat(String date){
		String dateval;
		String monthval;
		String yearval;
		
			if(!date.isEmpty()){
				
				if(date.contains("/"))
				{
					if(date.indexOf("/")!= -1 ){			
						yearval	= date.substring(0,date.indexOf("/"));	
						monthval = date.substring(date.indexOf("/")+1, date.lastIndexOf("/"));
						if(monthval.length()==1){
							monthval="0"+monthval;
						}			
						dateval  = date.substring(date.lastIndexOf("/")+1, date.length());
						if(dateval.length()==1){
							dateval="0"+dateval;
						}
					      date=yearval+"-"+monthval+"-"+dateval+" 00:00:00.000"; ;			
					//	ssnval=ssnval.replaceAll("[^0-9]", "");			
						
						}
				}else if(date.indexOf('-') != -1 ){			
					yearval	= date.substring(0,date.indexOf("-"));	
					monthval = date.substring(date.indexOf("-")+1, date.lastIndexOf("-"));
					if(monthval.length()==1){
						monthval="0"+monthval;
					}			
					dateval  = date.substring(date.lastIndexOf("-")+1, date.length());
					if(dateval.length()==1){
						dateval="0"+dateval;
					}
				      date=yearval+"-"+monthval+"-"+dateval+" 00:00:00.000"; ;
	
				}
				
			}
		return date;
	}
	
	public static String encodeFileToBase64Binary(String fileName) {
		String encodedString = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			byte[] bytes = bos.toByteArray();
			byte[] encoded = Base64.encodeBase64(bytes);
			encodedString = new String(encoded);
		} catch (IOException ex) {
			logger.error("Exception occured in encodeFileToBase64Binary "+ex.getMessage());
			//ex.printStackTrace();
		} finally {
			try {
				fis.close();
				/*
				 * delete zip file
				 */
				//new File(fileName).delete();
			} catch (IOException exception) {
				logger.error("Exception occured in encodeFileToBase64Binary "+exception.getMessage());
				//exception.printStackTrace();
			}
		}
		if (encodedString != null) {
			return encodedString;
		}
		return null;
	}
	
	public static AmazonS3 getAmazonS3Object(String accessKey, String secreteKey, String region, String bucketName, String sourcePatvh, String destinationPath) {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,secreteKey);
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(region)
				  .build();
		return s3client;
	}
	
	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {

		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);

		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
		folderName, emptyContent, metadata);

		// send request to S3 to create folder
		client.putObject(putObjectRequest);

		}
	
	public static List<String> getZipCodeList()
	{
		String arr[]=new String []{"20001",
				"20002",
				"20003",
				"20004",
				"20005",
				"20008",
				"20009",
				"20010",
				"20011",
				"20012",
				"20016",
				"20017",
				"20018",
				"20019",
				"20020",
				"20024",
				"20030",
				"20032",
				"20038",
				"20105",
				"20109",
				"20110",
				"20111",
				"20136",
				"20147",
				"20148",
				"20151",
				"20152",
				"20155",
				"20164",
				"20166",
				"20170",
				"20171",
				"20175",
				"20176",
				"20177",
				"20186",
				"20191",
				"20194",
				"20601",
				"20602",
				"20603",
				"20607",
				"20613",
				"20616",
				"20640",
				"20705",
				"20706",
				"20707",
				"20708",
				"20710",
				"20712",
				"20714",
				"20715",
				"20721",
				"20722",
				"20723",
				"20724",
				"20735",
				"20737",
				"20743",
				"20744",
				"20745",
				"20746",
				"20747",
				"20748",
				"20770",
				"20772",
				"20774",
				"20782",
				"20783",
				"20784",
				"20785",
				"20814",
				"20817",
				"20850",
				"20851",
				"20853",
				"20855",
				"20866",
				"20872",
				"20874",
				"20876",
				"20877",
				"20878",
				"20879",
				"20886",
				"20901",
				"20902",
				"20903",
				"20904",
				"20906",
				"20912",
				"21014",
				"21029",
				"21040",
				"21043",
				"21044",
				"21061",
				"21075",
				"21093",
				"21117",
				"21120",
				"21122",
				"21133",
				"21136",
				"21144",
				"21206",
				"21207",
				"21209",
				"21211",
				"21213",
				"21215",
				"21216",
				"21217",
				"21218",
				"21220",
				"21227",
				"21229",
				"21230",
				"21234",
				"21236",
				"21239",
				"21244",
				"21401",
				"21403",
				"21666",
				"21742",
				"21758",
				"21770",
				"21795",
				"21901",
				"21904",
				"21921",
				"22003",
				"22025",
				"22026",
				"22030",
				"22031",
				"22032",
				"22033",
				"22041",
				"22042",
				"22043",
				"22044",
				"22046",
				"22079",
				"22102",
				"22124",
				"22150",
				"22151",
				"22153",
				"22172",
				"22182",
				"22191",
				"22192",
				"22193",
				"22201",
				"22202",
				"22204",
				"22205",
				"22207",
				"22209",
				"22215",
				"22301",
				"22302",
				"22303",
				"22304",
				"22305",
				"22306",
				"22308",
				"22309",
				"22310",
				"22311",
				"22312",
				"22315",
				"22401",
				"22405",
				"22406",
				"22407",
				"22408",
				"22460",
				"22485",
				"22508",
				"22551",
				"22556",
				"22601",
				"22630",
				"22642",
				"22644",
				"22657",
				"22701",
				"22727",
				"22801",
				"22802",
				"22812",
				"22821",
				"22824",
				"22827",
				"22832",
				"22851",
				"22901",
				"22902",
				"22903",
				"22911",
				"22932",
				"22937",
				"22938",
				"22942",
				"23005",
				"23024",
				"23040",
				"23060",
				"23072",
				"23075",
				"23086",
				"23093",
				"23111",
				"23114",
				"23116",
				"23124",
				"23141",
				"23185",
				"23222",
				"23223",
				"23224",
				"23225",
				"23227",
				"23228",
				"23231",
				"23233",
				"23234",
				"23238",
				"23294",
				"23323",
				"23454",
				"23456",
				"23462",
				"23464",
				"23509",
				"23608",
				"23803",
				"23831",
				"23832",
				"23921",
				"23936",
				"24014",
				"24018",
				"24019",
				"24431",
				"24441",
				"24553",
				"24590",
				"28801"};
		return Arrays.asList(arr);
	}
}

