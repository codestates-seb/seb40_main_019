import '../css/DashboardBox.scss';

export default function DashboardBox({ title, num, unit, yellow }) {
  return (
    <div className="dashboardDetail">
      <h3>{title}</h3>
      <div>
        <h1 className={yellow}>{num}</h1>
        <h3>{unit}</h3>
      </div>
    </div>
  );
}
