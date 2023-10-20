'use client'

import styles from './page.module.css'
import {useEffect, useState} from "react";

function stackDigits(items: [number], onSelectedItem: any) {
  let stack = [];

  for(let i = 0; i < items.length; i++) {
    const item = (
        <button className="btn btn-outline-dark"
                style={{padding: 15}}
                key={i}
                onClick={(e) => {
                    e.preventDefault();
                    if(onSelectedItem) {
                        onSelectedItem(items[i]);
                    }
                }}
            ><span style={{ fontSize: 20 }}>{items[i]}</span>
        </button>
    );
    stack.push(item);
  }

  return (
      <div className="container">
        {stack}
      </div>
  );
}

export default function Home() {
    const [digits, setDigits] = useState([0, 1, 2, 3, 4, 5, 6, 7, 8, 9]);
    const [selectedDigit, setSelectedDigit] = useState(0);
    const [selectedDigits, setSelectedDigits] = useState([]);
    const [token, setToken] = useState("")
    const [isTokenValid, setIsTokenValid] = useState(false)

    useEffect(() => {
        fetchToken(selectedDigits);
    }, [selectedDigits]);
    useEffect(() => {
        validateToken(token);
    }, [token]);

    const fetchToken = async (selectedDigits: any) => {
        const body = {digits: selectedDigits};
        console.log(body);
        return fetch("http://localhost:8081/generator", {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify(body),
        });
    }
    const validateToken = async (token: string) => {
        return fetch("http://localhost:8082/validator/token=" + token, {
            method: "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
        });
    }
    const updateDigits = () => {
        const ind = digits.indexOf(selectedDigit);
        console.log("index is ", ind);
        selectedDigits.push(digits.splice(ind, 1));
        setSelectedDigits(selectedDigits);
        setDigits(digits);
    }

    return (
        <main className={styles.main}>
            <div>
                <p>
                    Generate tokens and validate them
                    <br/>
                    <code className={styles.code}>
                        Token has a format of XXXX-XXXX-XXXX-XXXX, and consists of the 0-9 digits
                    </code>
                </p>
            </div>
            <p>Available digits:</p>
            <div className="container">
                {stackDigits(digits,
                    (newDigit: number) => {
                        setSelectedDigit(newDigit);
                        updateDigits();
                    }
                )}
            </div>
            <hr/>
            <p>Selected digits:</p>
            <div className="container">
                {stackDigits(selectedDigits, null)}
            </div>
            <hr/>
            <p>Generate token</p>
            <button className="btn btn-sm btn-outline-dark"
                    style={{width: 'fit-content', padding: '10px 20px'}}
                    onClick={async (e) => {
                        e.preventDefault();
                        fetchToken(selectedDigits)
                            .then(async (resp) => {
                                const body = await resp.json();
                                console.log("resp: ", body);
                                setToken(body.token);
                            });
                    }}>
                Generate token
            </button>
            {token}
            <hr/>
            <p>Validate token</p>
            <button className="btn btn-sm btn-outline-dark"
                    style={{width: 'fit-content', padding: '10px 20px'}}
                    onClick={(e) => {
                        e.preventDefault();
                        validateToken(token)
                            .then(async (resp) => {
                                const body = await resp.json();
                                console.log("resp: ", body);
                                setIsTokenValid(body.valid);
                            });
                    }}>
                Validate token
            </button>
            {isTokenValid.toString()}
        </main>
    )
}
