import { CardBody, Center, Heading, Stack, Text, CardFooter, Button, Card } from "@chakra-ui/react";
import React, { useState, useEffect } from "react"
import { listarPautas } from '../../../api/assembleia';
import { PautaResponse } from "../../../types/PautaResponse";
import { useNavigate } from 'react-router-dom';

export function ListarPautasScreen() {


    const [pautas, setPautas] = useState<PautaResponse[]>([])
    const [loading, setLoading] = useState(false)
    const navigate = useNavigate()

    async function listar() {
        setLoading(true)
        const respose = await listarPautas()
        setPautas(respose?.data ?? [])
        setLoading(false)
    }

    useEffect(() => { listar() }, [])

    function Pauta(pauta: PautaResponse) {
        return <Card direction={{ base: 'column', sm: 'row' }}
            overflow='hidden'
            variant='outline'
        >
            <Stack>
                <CardBody>
                    <Text py='2'>
                        {pauta.descricao}
                    </Text>
                </CardBody>

                <CardFooter>
                    <Button type="button" variant='solid' colorScheme='whatsapp' onClick={() => navigate("/abrir-sessao")}>
                        Ver mais
                    </Button>
                </CardFooter>
            </Stack>
        </Card>
    }

    return <div className='pagina'>
        <Center>
            <Heading padding={5} size={'lg'}>Pautas</Heading>

        </Center>
        <div>
            {
                pautas.map(pauta => Pauta(pauta))
            }
        </div>

    </div>
}