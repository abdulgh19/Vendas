package br.com.Vendas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.Vendas.dao.FornecedorDao;
import br.com.Vendas.domain.Fornecedor;

@FacesConverter("fornecedorConverter")
public class FornecedorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente, String valor) {

		try {
			Long codigo = Long.parseLong(valor);
			FornecedorDao dao = new FornecedorDao();
			Fornecedor fornecedor = dao.buscarPorCodigo(codigo);

			return fornecedor;

		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente, Object objecto) {

		try {
			Fornecedor fornecedor = (Fornecedor) objecto;
			Long codigo = fornecedor.getCodigo();
			return codigo.toString();

		} catch (Exception e) {
			return null;
		}

	}

}
