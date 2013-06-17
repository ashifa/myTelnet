package com.ge.logparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

	static ArrayList<LogRecord> list = new ArrayList<LogRecord>();
	static DateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy",
			Locale.ENGLISH);
	static DateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd \n HH:mm:ss");

	public static void main(String[] args) throws IOException, ParseException {

		parseFile();

	}

	public static void parseFile() {
		URL targetInfo = Thread.currentThread().getContextClassLoader()
				.getResource("gesys_GEHC.log");
		BufferedReader reader;
		try {
			if (targetInfo != null) {

				reader = new BufferedReader(
						new FileReader(targetInfo.getFile()));

			} else {
				reader = new BufferedReader(new FileReader("gesys_GEHC.log"));
			}

			StringBuilder strBuild = new StringBuilder();
			String str;

			str = reader.readLine();
			while ((str = reader.readLine()) != null) {

				if (str.startsWith("SR ")) {
					strBuild.setLength(0);

				} else if (str.startsWith("EN ")) {

					String tmpStr = strBuild.toString();
					Pattern p = Pattern.compile(
							"\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} \\d{4}",
							Pattern.MULTILINE);
					Matcher m = p.matcher(tmpStr);
					if (m.find() == true) {
						String found = m.group().trim();
						Date date = sdf.parse(found);// "Fri Apr 18 17:22:06 2013"
						String[] dd = { dateFormatter.format(date), tmpStr };
						list.add(new LogRecord(date,tmpStr));
					}
				} else {
					strBuild.append(str + "\n");

				}

			}

			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

