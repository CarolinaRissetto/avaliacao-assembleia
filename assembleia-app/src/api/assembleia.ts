import axios from 'axios';
import { PautaResponse } from '../types/PautaResponse';

interface cadastrarPautaRequest{
    descricao: string
}

const API = process.env.REACT_APP_API

export async function cadastrarPauta(pauta: cadastrarPautaRequest): Promise<void> {
    try {
        return await axios.post(API  + "/cadastrar-pauta", pauta)
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