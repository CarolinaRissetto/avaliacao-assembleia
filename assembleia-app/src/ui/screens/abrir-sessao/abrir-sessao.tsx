import { CardBody, InputGroup, InputRightElement, ButtonGroup, HStack, Divider, Stack, Text, CardFooter, Button, Card, Input } from "@chakra-ui/react";
import { useNavigate } from 'react-router-dom';


export function AbrirSessaoScreen() {

    const navigate = useNavigate()


    return <div className="pagina"><Card maxW='sm'>
        <CardBody>
            <Stack mt='6' spacing='3'>
                <Text>
                    This sofa is perfect for modern tropical spaces, baroque inspired
                    spaces, earthy toned spaces and for people who love a chic design with a
                    sprinkle of vintage design.
                </Text>
            </Stack>
        </CardBody>
        <Divider />

        <div className='votacao-input'>
            <Input placeholder='Digite seu cpf para votar' marginTop={'10px'} />
        </div>

        <CardFooter justify='center' flexWrap='wrap'>
            <Stack direction='column' alignItems='center' justifyContent={'center'}>
                <ButtonGroup spacing='2' paddingY={'10px'}>
                    <Stack direction='row' alignItems={'center'}>
                        <Button variant='solid' colorScheme='whatsapp'>
                            Votar a favor
                        </Button>
                        <Button variant='solid' colorScheme='red'>
                            Votar contra
                        </Button>
                    </Stack>
                </ButtonGroup>

                <Divider borderColor='black' />
                <Stack direction='row' alignItems={'center'} paddingY='20px'>
                    <Input placeholder='tempo de sessao' width='max-content' />
                    <Button variant='outline' colorScheme='whatsapp' onClick={() => navigate("/")}>
                        Iniciar votação
                    </Button>
                </Stack>

                <Divider borderColor='black' />

                <Button variant='outline' colorScheme='whatsapp' onClick={() => navigate("/")}>
                    Voltar
                </Button>
            </Stack>
        </CardFooter>
    </Card>
    </div>
}