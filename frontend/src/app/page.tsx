'use client'

import styles from './page.module.css'
import {useState} from "react";

function stackDigits(items: [number], onSelectedItem: any) {
  let stack = [];

  for(let i = 0; i < items.length; i++) {
    const item = (
        <button className="btn btn-outline-dark"
                style={{padding: 15}}
                onClick={(e) => {
                    e.preventDefault();
                    onSelectedItem(items[i]);
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
    const [digits, setDigits] = useState([0,1,2,3,4,5,6,7,8,9])
    const [selectedDigits, setSelectedDigits] = useState([])

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
        { stackDigits(digits,
            (selectedDigit: number) => {
                    digits.splice(digits.indexOf(selectedDigit), 1);
                    setDigits(digits);
                    selectedDigits.push(selectedDigit);
                    setSelectedDigits(selectedDigits);
                }
            )
        }
      </div>
        <hr/>
        <p>Selected digits:</p>
        <div className="container">
            { stackDigits(selectedDigits, null)}
        </div>
        <hr/>
        <p>Generate token</p>
        <button className="btn btn-sm btn-outline-dark"
                style={{width: 'fit-content', padding: '10px 20px'}}
                onClick={(e) => {
                    e.preventDefault();

                }}>
            Generate token
        </button>
        <hr/>
        <p>Validate token</p>
        <button className="btn btn-sm btn-outline-dark"
                style={{width: 'fit-content', padding: '10px 20px'}}
                onClick={(e) => {
                    e.preventDefault();

                }}>
            Validate token
        </button>
    </main>
  )
}
