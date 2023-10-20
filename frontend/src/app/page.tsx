import styles from './page.module.css'

export default function Home() {
  return (
    <main className={styles.main}>
      <div className={styles.description}>
        <p>
          Generate tokens and validate them
          <br/>
          <code className={styles.code}>
            Token has a format of XXXX-XXXX-XXXX-XXXX, and consists of the 0-9 digits
          </code>
        </p>
        <div>
          <div>
            <button className="btn btn-sm btn-outline-dark">1</button>
          </div>
        </div>
      </div>
    </main>
  )
}
