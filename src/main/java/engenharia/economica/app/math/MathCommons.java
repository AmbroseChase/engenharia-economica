package engenharia.economica.app.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import engenharia.economica.app.dto.PeriodoDTO;
import engenharia.economica.app.dto.TaxaDTO;
import engenharia.economica.app.enums.TiposTempoJuros;

public final class MathCommons {
    
    private MathCommons() {
    }
    
    public static final double DIAS_NO_MES_DOUBLE = 30.0;
    public static final double MESES_NO_ANO_DOUBLE = 12.0;
    public static final double DIAS_NO_ANO_DOUBLE = 365.0;
    public static final BigDecimal DIAS_NO_MES_BIG_DEC = new BigDecimal(30);
    public static final BigDecimal MESES_NO_ANO_BIG_DEC = new BigDecimal(12);
    public static final BigDecimal DIAS_NO_ANO_BIG_DEC = new BigDecimal(365);
    public static final MathContext MATH_CONTEXT_2 = new MathContext(2, RoundingMode.HALF_EVEN);
    public static final MathContext MATH_CONTEXT_100000 = new MathContext(100000, RoundingMode.HALF_EVEN);
    
    public static BigDecimal converterTemposMesmaProporcao(String tipoTempoTaxa, PeriodoDTO periodoDTO) {
	String tipoTempoPeriodo = periodoDTO.getTipoTempoPeriodo();
	BigDecimal vlrPeriodo   = periodoDTO.getVlrPeriodo();
	
	if (!tipoTempoPeriodo.equals(tipoTempoTaxa)) {
	    BigDecimal vlrPeriodoConvertido = null;
	    
	    if(tipoTempoTaxa.equals(TiposTempoJuros.M.name())) {
		if(tipoTempoPeriodo.equals(TiposTempoJuros.D.name())) {
		    vlrPeriodoConvertido = vlrPeriodo.divide(DIAS_NO_MES_BIG_DEC, MATH_CONTEXT_100000);
		} else {
		    vlrPeriodoConvertido = vlrPeriodo.multiply(MathCommons.MESES_NO_ANO_BIG_DEC);
		}
	    } else if(tipoTempoTaxa.equals(TiposTempoJuros.A.name())) {
		if(tipoTempoPeriodo.equals(TiposTempoJuros.D.name())) {
		    vlrPeriodoConvertido = vlrPeriodo.divide(MathCommons.DIAS_NO_ANO_BIG_DEC, MATH_CONTEXT_100000);
		} else {
		    vlrPeriodoConvertido = vlrPeriodo.divide(MathCommons.MESES_NO_ANO_BIG_DEC, MATH_CONTEXT_100000);
		}
	    } else {
		if(tipoTempoPeriodo.equals(TiposTempoJuros.M.name())) {
		    vlrPeriodoConvertido = vlrPeriodo.multiply(MathCommons.DIAS_NO_MES_BIG_DEC);
		} else {
		    vlrPeriodoConvertido = vlrPeriodo.multiply(MathCommons.DIAS_NO_ANO_BIG_DEC);
		}
	    }
	    
	    return vlrPeriodoConvertido;
	}
	
	tipoTempoPeriodo = null;
	
	return vlrPeriodo;
    }
    public static BigDecimal converterTemposMesmaProporcaoBaseTaxa(TaxaDTO taxaDTO, String tipoTempoDepositos) {
	String tipoTempoTaxa  = taxaDTO.getTipoTempoTaxa();
	BigDecimal taxaPorCem = taxaDTO.obterTaxaPor100();
	
	if (!tipoTempoDepositos.equals(tipoTempoTaxa)) {
	    double vlrTaxaConvertido;
	    BigDecimal umMaisTaxa = BigDecimal.ONE.add(taxaPorCem);
	    double umMaisVlrTaxaDouble = umMaisTaxa.doubleValue();
	    double umDouble = 1.0;
	    
	    if(tipoTempoTaxa.equals(TiposTempoJuros.M.name())) {
		if(tipoTempoDepositos.equals(TiposTempoJuros.D.name())) {
		    vlrTaxaConvertido = Math.pow(umMaisVlrTaxaDouble, umDouble/DIAS_NO_MES_DOUBLE) - umDouble;
		} else {
		    vlrTaxaConvertido = Math.pow(umMaisVlrTaxaDouble, MESES_NO_ANO_DOUBLE) - umDouble;
		}
	    } else if(tipoTempoTaxa.equals(TiposTempoJuros.A.name())) {
		if(tipoTempoDepositos.equals(TiposTempoJuros.D.name())) {
		    vlrTaxaConvertido = Math.pow(umMaisVlrTaxaDouble, umDouble/DIAS_NO_ANO_DOUBLE) - umDouble;
		} else {
		    vlrTaxaConvertido = Math.pow(umMaisVlrTaxaDouble, umDouble/MESES_NO_ANO_DOUBLE) - umDouble;
		}
	    } else {
		if(tipoTempoDepositos.equals(TiposTempoJuros.M.name())) {
		    vlrTaxaConvertido = Math.pow(umMaisVlrTaxaDouble, DIAS_NO_MES_DOUBLE) - umDouble;
		} else {
		    double umMaisVlrTaxaElevadoAnoDouble = Math.pow(umMaisVlrTaxaDouble, DIAS_NO_ANO_DOUBLE);
		    
		    if (!Double.isFinite(umMaisVlrTaxaElevadoAnoDouble)) {
			return BigDecimalMath.pow(umMaisTaxa, DIAS_NO_ANO_BIG_DEC).subtract(BigDecimal.ONE);
		    }
		    
		    vlrTaxaConvertido = umMaisVlrTaxaElevadoAnoDouble - umDouble;
		}
	    }
	    
	    umMaisTaxa = null;
	    
	    return BigDecimal.valueOf(vlrTaxaConvertido);
	}
	
	tipoTempoTaxa = null;
	
	return taxaPorCem;
    }
}