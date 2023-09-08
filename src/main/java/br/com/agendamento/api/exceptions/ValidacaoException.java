package br.com.agendamento.api.exceptions;

/**
 * Classe para mensagem personalizada de validação.
 * @author leovizeu
 */
public class ValidacaoException extends Exception{
    public ValidacaoException (String msg){
        super("Erro na validação! " + msg);
    }
}
