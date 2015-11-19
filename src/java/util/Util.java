/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cristiano
 */
public class Util {

    public static void FacesContextAddMessage(FacesMessage.Severity severity, String summary, String detail, String clientId) {
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(
                severity,
                summary,
                detail);
        contexto.addMessage(clientId, mensagem);
    }

    public static boolean isNumerico(String valor) {
        try {
            long parseLong = Long.parseLong(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String valor) {
        try {
            Double parseDouble = Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInt(String valor) {
        try {
            Integer parseInt = Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica se o cpf informado não é seguido dos mesmo caracteres e se é
     * igual a 11 digitos
     *
     * @param cpf
     * @return Verifica se os dois últimos digitos do cpf são dos digitos
     * verificadores
     */
    public static boolean isCPF(String cpf) {
        //verifica se o cpf é formado por uma sequencia de numeros iguais ou menor que 11 digitos
        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")
                || !Util.isNumerico(cpf)
                || (cpf.length() != 11)) {
            return (false);
        }

        char digito10 = 0, digito11;
        int sm, i, r, num, peso;

        try {
            //calculo do primeiro digito verificador
            sm = 0;
            peso = 10;

            for (i = 0; i < 9; i++) {
                //converte o i-esimo caractere do cpf em um numero:
                //No caso transforma o caractere '0' no inteiro 0
                //(48 eh a posição de  '0' na tabela ASCII
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);

                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                digito10 = '0';

            } else {
                digito10 = (char) (r + 48);//converte no respectivo caractere
            }

            //calculo do segundo digito verificador
            sm = 0;
            peso = 11;

            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);

                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                digito11 = '0';
            } else {
                digito11 = (char) (r + 48);
            }

            //Verifica se os digitos calculados conferem com os digitos informados.
            if ((digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10))) {
                return (true);

            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return false;
        }
    }

    /**
     * *
     * Compara duas datas, verificando se a primeira data é maior que a segunda
     * data.
     *
     * @param dataIni
     * @param dataFim
     * @return
     */
    public static boolean isDataMaior(Date dataIni, Date dataFim) {

        if (dataIni == null || dataFim == null) {
            return false;
        }

        if (dataIni.compareTo(dataFim) < 0) {
            return false;
        } else {
            return true;
        }

        //return dataIni.after(dataFim);
    }
    
    /**
     * Compara a hora em duas datas. Retorna null caso qualquer uma das datas seja nula.
     * @param dtInicio
     * @param dtFim
     * @return 
     */
    public static Date dateDiff_Hour(Date dtInicio, Date dtFim){
        Date data;
        if (dtInicio == null || dtFim == null) {
            return null;
        } else {
            Calendar calIni = Calendar.getInstance();
            Calendar calFim = Calendar.getInstance();

            calIni.setTimeInMillis(dtInicio.getTime());
            calFim.setTimeInMillis(dtFim.getTime());

            Calendar calDiff = Calendar.getInstance();

            calDiff.set(Calendar.HOUR_OF_DAY, calFim.get(Calendar.HOUR_OF_DAY) - calIni.get(Calendar.HOUR_OF_DAY));
            calDiff.set(Calendar.MINUTE, calFim.get(Calendar.MINUTE) - calIni.get(Calendar.MINUTE));

            data = calDiff.getTime();

            return data;
        }
    }
}
