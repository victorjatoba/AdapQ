package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import data.UserModel;

public class Util {

	private static Object objectSycron;
	static {
		objectSycron = new Object();
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object value) {

		if (value == null) {
			return true;
		} else if (value instanceof Collection) {
			return ((Collection) value).isEmpty();
		}

		return false;
	}

	public static UserModel getUserLogged() {

		UserModel user = null;

		try {
			user = (UserModel) FacesUtil.getObjectInSession(Constants.USER_LOGGED);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public static String convertDateToString(Date date, String formato) {
		if (!ValidationUtil.isNullOrEmpty(date) && !ValidationUtil.isNullOrEmpty(formato)) {
			SimpleDateFormat format = new SimpleDateFormat(formato);
			return format.format(date);
		}
		return null;
	}

	// metodo que retorna o intervalo de dias entre duas datas
	private static String contaDias(String dataInicialBR, String dataFinalBR) throws ParseException {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		Date dataInicio = df.parse(dataInicialBR);
		Date dataFim = df.parse(dataFinalBR);
		long dt = (dataFim.getTime() - dataInicio.getTime()) + 3600000;
		Long diasCorridosAnoLong = (dt / 86400000L);

		Integer diasDecorridosInt = Integer.valueOf(diasCorridosAnoLong.toString());

		String diasDecorridos = String.valueOf(diasDecorridosInt); // Sem numeros formatados;

		return diasDecorridos;

	}

	// m�todo para pegar a data do dia
	private static String getDateDayBr() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String diaIguana = df.format(new Date());
		return diaIguana;
	}

	// agora para calcular a idade
	public static BigDecimal calculaIdade(String birthDate) throws ParseException {
		BigDecimal qtdDias = new BigDecimal(contaDias(birthDate, getDateDayBr()));
		BigDecimal ano = new BigDecimal(365.25);
		BigDecimal age = qtdDias.divide(ano, 0, RoundingMode.DOWN);

		return age;
	}

	public static String dayOfWeek() {

		GregorianCalendar calendario = new GregorianCalendar();
		int dayOfWeek = calendario.get(Calendar.DAY_OF_WEEK);

		String day = null;

		switch (dayOfWeek) {
		case 1: {
			day = "6";
			break;
		}
		case 2: {
			day = "0";
			break;
		}
		case 3: {
			day = "1";
			break;
		}
		case 4: {
			day = "2";
			break;
		}
		case 5: {
			day = "3";
			break;
		}
		case 6: {
			day = "4";
			break;
		}
		case 7: {
			day = "5";
			break;
		}

		}
		return day;

	}

	public static String periodOfDay() {

		GregorianCalendar calendario = new GregorianCalendar();
		int hourOfDay = calendario.get(Calendar.HOUR_OF_DAY);

		String period = null;

		if (hourOfDay >= 6 && hourOfDay < 12) {
			period = "0";
		} else if (hourOfDay >= 12 && hourOfDay < 18) {
			period = "1";
		} else if (hourOfDay >= 18 && hourOfDay < 24) {
			period = "2";
		}

		return period;

	}

	public static String letterOption(int num) {
		String letter = null;

		switch (num) {
		case 0: {
			letter = "a)";
			break;
		}
		case 1: {
			letter = "b)";
			break;
		}
		case 2: {
			letter = "c)";
			break;
		}
		case 3: {
			letter = "d)";
			break;
		}
		case 4: {
			letter = "e)";
			break;
		}

		}

		return letter;

	}

}
