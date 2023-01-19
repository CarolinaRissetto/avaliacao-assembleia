export interface PautaResponse {
    id: number,
    descricao: string,
    dataHoraAberturaSessao?: Date,
    tempoDuracao?: number,
    votoSim: number,
    votoNao: number
}