import '../css/footer.scss';
import footer from '../../../assets/img/footer.png';
export default function Footer() {
  return (
    <>
      <footer>
        <img src={footer} alt="footer" />
        <section className="footer">
          <div className="footerContainer">
            <div className="firstBox">
              <div className="titleDiv">LUXMEAL</div>
              <div className="aTag">
                <a
                  href="https://github.com/codestates-seb/seb40_main_019"
                  target="blank"
                >
                  • Repository Wiki
                </a>
                <a
                  href="https://github.com/codestates-seb/seb40_main_019/wiki"
                  target="blank"
                >
                  • Demo Video
                </a>
              </div>
            </div>

            <div className="secondBox">
              LUXMEAL © 2022 All rights reserved.{' '}
            </div>

            <div className="thirdBox">
              <div>
                <a href="https://github.com/Liieonking" target="blank">
                  <i className="fa-brands fa-github" />
                  손현지
                </a>
                <a href="https://github.com/shinker1002" target="blank">
                  <i className="fa-brands fa-github" />
                  최민수
                </a>
                <a href="https://github.com/GitHubJIYEON" target="blank">
                  <i className="fa-brands fa-github" />
                  이지연
                </a>
              </div>
              <div>
                <a href="https://github.com/hyojoonm" target="blank">
                  <i className="fa-brands fa-github" />
                  김효준
                </a>
                <a href="https://github.com/Taekgil99" target="blank">
                  <i className="fa-brands fa-github" />
                  박재원
                </a>
                <a href="https://github.com/junohheo" target="blank">
                  <i className="fa-brands fa-github" />
                  허준오
                </a>
              </div>
            </div>
          </div>
        </section>
      </footer>
    </>
  );
}
