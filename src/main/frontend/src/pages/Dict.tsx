import React, {useEffect, useState} from 'react';
import {useSearchParams} from "react-router-dom";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import {ListGroup} from "react-bootstrap";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Spacer from "./Spacer"

const Dict = () => {

    const [rsp, setRsp] = useState<any[]>([]);
    const [queryParameters] = useSearchParams()

    useEffect( () => {
        if( queryParameters.get("lang") === null) {
            setRsp([])
        }
        else {
            fetch("/rest/dict", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({lang: queryParameters.get("lang"), query: queryParameters.get("query")})
            })
                .then(response => {
                    if( response.ok) {
                        return response.json()
                    }

                    throw new Error();
                })
                .then(data => setRsp(data.dictEntries))
                .catch(err => console.log(err))
        }

    }, [queryParameters]);

    const handleSubmit = async (event:any) => {
        const form = event.currentTarget;
        event.preventDefault();
        event.stopPropagation();
        try {
            const HTTP_TIMEOUT = 10000;
            const controller = new AbortController();
            const timeoutId = setTimeout(() => controller.abort(), HTTP_TIMEOUT);

            const response = await fetch("/rest/flashcard/save", {
                method: "POST",
                cache: "no-cache",
                signal: controller.signal,
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({front: form.front.value, back: form.back.value})
            });

            if( response.ok) {
                form.back.value = "";
                form.front.value = "";
            }

        } catch (error) {
            console.error("Error:", error);
        }
    };

    return (
        <div>
            <Spacer size={75} axis={"horizontal"} />
            <Container>
                <Row >
                    <Col sm={8} >
                        <div className="overflow-auto" style={{
                            overflow: "auto",
                            width: "100%",
                            height: 950,
                            flexDirection: "column",
                            justifyContent: "flex-start",
                        }}
                        >
                        {
                            rsp.map( (e) => <div>{
                                e.hits.map( (h:any) =>
                                    <div > {
                                        h.roms.map( (x:any) => {
                                            const markup = { __html: x.headword_full };
                                            return (
                                                <div >
                                                    <h4 >{x.headword}</h4>
                                                    <div dangerouslySetInnerHTML={markup} />
                                                    <div>{
                                                        x.arabs.map( (y:any) => {
                                                            const yMarkup = { __html: y.header }
                                                            return (
                                                            <div style={{margin: '20px'}}>
                                                                <div dangerouslySetInnerHTML={yMarkup} />
                                                                <div >{
                                                                    y.translations.map((t:any) => {
                                                                        const tsMarkup = { __html: t.source }
                                                                        const ttMarkup = { __html: t.target }
                                                                        return (
                                                                            <ListGroup style={{margin: '20px'}} >
                                                                                <ListGroup.Item style={{backgroundColor: 'whitesmoke'}}>
                                                                                    <div dangerouslySetInnerHTML={tsMarkup} />
                                                                                </ListGroup.Item>
                                                                                <ListGroup.Item style={{backgroundColor: 'whitesmoke'}}>
                                                                                    <div dangerouslySetInnerHTML={ttMarkup} />
                                                                                </ListGroup.Item>
                                                                            </ListGroup>
                                                                        )
                                                                    })
                                                                }
                                                                </div>
                                                            </div>
                                                            )}
                                                        )
                                                    }</div>
                                                    <br />
                                                </div>
                                            )
                                        })
                                    }</div>
                                )
                            }</div> )
                        }
                        </div>
                    </Col>
                    <Col sm={4}>
                        <Form onSubmit={handleSubmit}>
                            <Form.Group className="mb-3" controlId="formBasicEmail">
                                <Form.Label>Przód</Form.Label>
                                <Form.Control as="textarea" rows={3} name="front"/>
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formBasicPassword">
                                <Form.Label>Tył</Form.Label>
                                <Form.Control as="textarea" rows={3} name="back" />
                            </Form.Group>
                            <Button variant="primary" type="submit">
                                Zapisz
                            </Button>
                        </Form>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default Dict;
