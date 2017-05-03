
public class CalculoValor {
	
	int quantidadeHoras; 
	int quantidadeMinutos;
	Acesso _acesso;
	
	public static final float VALOR_FRACAO = (float) 2.0;
	public static final float VALOR_HORA = (float) 7.0;
	public static final float VALOR_DIARIA = (float) 30.0;
	
	
	public CalculoValor(Acesso _acesso) {
		this._acesso = _acesso;
	}

	public float computar(){
		if (_acesso.horaSaida == _acesso.horaEntrada){
			quantidadeMinutos = calcularMinutos();
			quantidadeHoras = 0;
		}
			
		else if (_acesso.horaSaida > _acesso.horaEntrada && _acesso.minutosEntrada == _acesso.minutosSaida){
			quantidadeMinutos = 0;
			quantidadeHoras = calcularHoras();
		}
		else if (_acesso.horaSaida > _acesso.horaEntrada && _acesso.minutosEntrada > _acesso.minutosSaida) {
			quantidadeMinutos = calcularMinutos();
			quantidadeHoras = 0;
		}
			
		else if (_acesso.horaSaida > _acesso.horaEntrada && _acesso.minutosSaida < _acesso.minutosEntrada){
			quantidadeMinutos = calcularMinutos();
			quantidadeHoras = calcularHoras() - 1;
		}
		else {
			quantidadeHoras = 0;
			quantidadeMinutos = 0;
		}
		
		float valorTotal = 0; 
		valorTotal += quantidadeHoras * VALOR_HORA;
		valorTotal += Math.ceil(quantidadeMinutos / 15.0) * VALOR_FRACAO;		
		
		if (quantidadeHoras >=9)
			return VALOR_DIARIA;
		else 
			return valorTotal;
	}
	
	public int calcularMinutos(){
		
		if(_acesso.horaSaida > _acesso.horaEntrada && _acesso.minutosSaida < _acesso.minutosEntrada)
			return _acesso.minutosSaida + (60 - _acesso.minutosEntrada);
		return _acesso.minutosSaida - _acesso.minutosEntrada;
	}
	
	public int calcularHoras(){
		return _acesso.horaSaida - _acesso.horaEntrada;
	}

}
