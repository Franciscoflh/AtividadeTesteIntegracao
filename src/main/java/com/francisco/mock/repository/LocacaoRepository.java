package com.francisco.mock.repository;

import com.francisco.mock.model.Locacao;

public interface LocacaoRepository {
    public Locacao getById(Integer idLocacao);

    public Locacao salvar(Locacao locacao);

    public boolean atualizar(Locacao locacao);

    public boolean deletar(Locacao locacao);
}
