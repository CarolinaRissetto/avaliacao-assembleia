import { Heading, Center, Textarea, Button, FormControl, FormLabel, Stack } from '@chakra-ui/react'
import { FormEvent, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { cadastrarPauta } from '../../../api/assembleia';

export function CadastrarPautaScreen() {

    const [descricao, setDescricao] = useState<string | undefined>(undefined);
    const [loading, setLoading] = useState(false)

    const navigate = useNavigate()

    async function onSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        if (descricao !== undefined)
        {
            setLoading(true)
            await cadastrarPauta({ descricao })
            setLoading(false)
            navigate("/")
        }
    }

    return <div className='pagina'>
        <Center>
            <Heading size={'lg'}>Cadastrar pauta</Heading>
        </Center>
        <form noValidate onSubmit={event => onSubmit(event)}>
            <FormControl isInvalid={descricao === ""}>
                <FormLabel>Descrição:</FormLabel>
                <Textarea  size="md" isRequired placeholder="Descreva sua pauta" value={descricao} onChange={event => setDescricao(event.target.value)} />
            </FormControl>

            <Stack paddingTop={5} spacing={4} direction='row' align='center'>
                <Button type="button" colorScheme='whatsapp' variant='outline' size='xs' onClick={() => navigate("/")}>
                    Voltar
                </Button>
                <Button isLoading={loading} type="submit" colorScheme='whatsapp' size='xs' isDisabled={descricao === ""}>
                    Cadastrar
                </Button>
            </Stack>
        </form>
    </div>
}