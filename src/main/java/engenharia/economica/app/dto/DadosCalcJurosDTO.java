package engenharia.economica.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DadosCalcJurosDTO implements Serializable {
    
    private static final long serialVersionUID = 5977885967584018270L;
    
    private BigDecimal capitalInicial;
    private TaxaDTO    taxaJurosDTO;
    private PeriodoDTO tempoEmprestDTO;
    private String     tipoJuros;
    
    public BigDecimal getCapitalInicial() {
	return capitalInicial;
    }
    
    public void setCapitalInicial(BigDecimal capitalInicial) {
	this.capitalInicial = capitalInicial;
    }
    
    public TaxaDTO getTaxaJurosDTO() {
	return taxaJurosDTO;
    }
    
    public void setTaxaJurosDTO(TaxaDTO taxaJurosDTO) {
	this.taxaJurosDTO = taxaJurosDTO;
    }
    
    public PeriodoDTO getTempoEmprestDTO() {
	return tempoEmprestDTO;
    }
    
    public void setTempoEmprestDTO(PeriodoDTO tempoEmprestDTO) {
	this.tempoEmprestDTO = tempoEmprestDTO;
    }
    
    public String getTipoJuros() {
	return tipoJuros;
    }
    
    public void setTipoJuros(String tipoJuros) {
	this.tipoJuros = tipoJuros;
    }
}