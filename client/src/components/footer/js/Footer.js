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
                <a href="https://github.com/codestates-seb/seb40_main_019">
                  • Repository Wiki
                </a>
                <a href="https://github.com/codestates-seb/seb40_main_019/wiki">
                  • Demo Video
                </a>
              </div>
            </div>

            <div className="secondBox">
              LUXMEAL © 2022 All rights reserved.{' '}
            </div>
            <div className="thirdBox">
              <div className="frontMember">
                <a href="https://github.com/Liieonking">
                  <i className="fa-brands fa-github" />
                  손현지
                </a>
                <a href="https://github.com/shinker1002">
                  <i className="fa-brands fa-github" />
                  최민수
                </a>
                <a href="https://github.com/GitHubJIYEON">
                  <i className="fa-brands fa-github" />
                  이지연
                </a>
              </div>
              <div className="backMember">
                <a href="https://github.com/hyojoonm">
                  <i className="fa-brands fa-github" />
                  김효준
                </a>
                <a href="https://github.com/Taekgil99">
                  <i className="fa-brands fa-github" />
                  박재원
                </a>
                <a href="https://github.com/junohheo">
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
