package br.com.uol.domain.exception;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException  {

    private static final long serialVersionUID = 1L;

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
    public ClienteNaoEncontradoException(Long clienteId) {
        this(String.format("Não existe um cadastro de Cliente com código %d",clienteId));
    }

}
