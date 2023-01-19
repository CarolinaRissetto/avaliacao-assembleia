import axios, { AxiosError } from 'axios';
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

export async function cadastrarPauta(pauta: cadastrarPautaRequest): Promise<string | void> {
    try {
        return await axios.post(API + "/cadastrar-pauta", pauta)
    } catch (error) {
        console.log(error)
    }
}

export async function listarPautas() {
    try {
        return await axios.get<PautaResponse[]>(API + "/pautas")
    } catch (error) {
        console.log(error)
    }
}

export async function votar(votacao: votacaoRequest): Promise<undefined | string> {
    try {
        return await axios.post(API + "/votar", votacao)

        return undefined
    } catch (error) {
        const axiosError = error as AxiosError<string>
        return axiosError?.response?.data
    }
}

export async function abrirSessao(sessaoVotacao: abrirSessaoRequest): Promise<undefined | string> {
    try {
        await axios.post(API + "/abrir-sessao", sessaoVotacao)

        return undefined
    } catch (error) {
        const axiosError = error as AxiosError<string>
        return axiosError?.response?.data
    }
}

