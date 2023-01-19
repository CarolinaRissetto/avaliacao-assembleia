import axios from 'axios';
import { PautaResponse } from '../types/PautaResponse';

interface cadastrarPautaRequest {
    descricao: string
}

interface votacaoRequest {
    idPauta: number,
    cpf: string,
    voto: boolean,
}

interface abrirSessaoRequest {
    id: number,
    tempoDuracao: number
}

const API = process.env.REACT_APP_API

export async function cadastrarPauta(pauta: cadastrarPautaRequest): Promise<void> {
    try {
        return await axios.post(API + "/cadastrar-pauta", pauta)
    } catch (error) {
        console.error(error)
    }
}

export async function listarPautas() {
    try {
        return await axios.get<PautaResponse[]>(API + "/pautas")
    } catch (error) {
        console.log(error)
    }
}

export async function votar(votacao: votacaoRequest): Promise<void> {
    try {
        return await axios.post(API + "/votar", votacao)
    } catch (error) {
        console.error(error)
    }
}

export async function abrirSessao(sessaoVotacao: abrirSessaoRequest): Promise<void> {
    try {
        return await axios.post(API + "/abrir-sessao", sessaoVotacao)
    } catch (error) {
        console.error(error)
    }
}

