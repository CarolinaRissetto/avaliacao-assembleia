import { CardBody, InputGroup, InputRightElement, ButtonGroup, HStack, Divider, Stack, Text, CardFooter, Button, Card, Input } from "@chakra-ui/react";
import { useNavigate, useLocation } from 'react-router-dom';
import { PautaResponse } from "../../../types/PautaResponse";
import { FormEvent, useState } from 'react'
import { abrirSessao, votar } from '../../../api/assembleia';


export function AbrirSessaoScreen() {

    const navigate = useNavigate()
    const location = useLocation();
    const pauta = location.state.pauta as PautaResponse
    const [loading, setLoading] = useState(false)
    const [duracao, setDuracao] = useState<number>(1);
    const [cpfAssociado, setCpfAssociado] = useState<string>("");


    async function onSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        setLoading(true)
        await abrirSessao({ id: pauta.id, tempoDuracao: duracao })
        setLoading(false)
        navigate("/")
    }

    async function onSubmitVoto(voto: boolean) {
        setLoading(true)
        await votar({ idPauta: pauta.id, cpf: cpfAssociado, voto: voto })
        setLoading(false)
        navigate("/")
    }

    return <div className="pagina"><center><Card maxW='sm'>
        <CardBody>
            <Stack mt='6' spacing='3'>
                <Text fontSize='20px' color='gray' as='i'>
                    Esta pauta teve {pauta.votoSim} votos a favor  e {pauta.votoNao} votos contra na ultima sessão.
                </Text>
                <Text>
                    {pauta.descricao}
                </Text>
            </Stack>
        </CardBody>
        <Divider />

        <div className='votacao-input'>
            <Input placeholder='Digite seu cpf para votar' marginTop={'10px'} value={cpfAssociado} onChange={event => setCpfAssociado(event.target.value)} />
        </div>

        <CardFooter justify='center' flexWrap='wrap'>
            <Stack direction='column' alignItems='center' justifyContent={'center'}>

                <form noValidate onSubmit={event => event.preventDefault()}>

                    <ButtonGroup spacing='2' paddingY={'10px'}>
                        <Stack direction='row' alignItems={'center'}>
                            <Button type="button" variant='solid' colorScheme='whatsapp' onClick={() => onSubmitVoto(true)}>
                                Votar a favor
                            </Button>
                            <Button type="button" variant='solid' colorScheme='red' onClick={() => onSubmitVoto(false)}>
                                Votar contra
                            </Button>
                        </Stack>
                    </ButtonGroup>
                </form>
                <Divider borderColor='black' />

                <form noValidate onSubmit={event => onSubmit(event)}>
                    <Stack direction='row' alignItems={'center'} paddingY='20px'>
                        <Input type={"number"} placeholder='tempo de sessao' width='max-content' value={duracao} onChange={event => setDuracao(event.target.valueAsNumber)} />
                        <Button isLoading={loading} type="submit" variant='outline' colorScheme='whatsapp'>
                            Iniciar votação
                        </Button>
                    </Stack>
                </form>

                <Divider borderColor='black' />

                <Button variant='outline' colorScheme='whatsapp' onClick={() => navigate("/")}>
                    Voltar
                </Button>
            </Stack>
        </CardFooter>
    </Card>
    </center>
    </div>
}