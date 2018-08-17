<?php

require_once "../vendor/autoload.php";
require_once "../db/connection.php";
require_once "../reports/mem_image.php";

session_start();
        if((!isset ($_SESSION['login']) == true) and (!isset ($_SESSION['password']) == true))
        {
            unset($_SESSION['login']);
            unset($_SESSION['password']);
      header('location: ../login.php');
        }else{

$query = "SELECT s.str_name estado, sum(p.tb_beneficiaries_id_beneficiaries) qtde, p.int_month mes , p.int_year ano
	FROM tb_payments p 
	inner join tb_city c 
        on  p.tb_city_id_city = c.id_city 
	inner join tb_state s 
        on c.tb_state_id_state = s.id_state
        where p.int_year in (SELECT max(p.int_year) ano FROM tb_payments p) 
        and p.int_month in (SELECT max(p.int_month) mes FROM tb_payments p)
        group by s.id_state, p.int_year, p.int_month 
        order by p.int_year desc, p.int_month desc, s.str_name asc;";

$statement = $pdo->prepare($query);
$statement->execute();
$rs = $statement->fetchAll(PDO::FETCH_ASSOC);

foreach ($rs as $value) {
    $resultado[] = $value;
}

if (isset($resultado)) {
    foreach ($resultado as $r) {
        $data[] = [utf8_decode($r['estado']), $r['qtde']];
        $mesano = $r['mes'].'/'.$r['ano'];
    }
} else {
    $data[] = [null, null];
}
$grafico = new \PHPlot(800, 400);
//$grafico = new PHPlot(800, 400);
$grafico->SetImageBorderType('plain');
$grafico->SetPlotType('bars');
$grafico->SetDataType('text-data');
$grafico->SetDataValues($data);
$grafico->SetTitle(utf8_decode("Total beneficiaries by state in: "). $mesano);

# Turn off X tick labels and ticks because they don't apply here:
$grafico->SetXTickLabelPos('none');
$grafico->SetXTickPos('none');
$grafico->SetXLabelAngle(90);
# Make sure Y=0 is displayed:
$grafico->SetPlotAreaWorld(NULL, 0);
# Y Tick marks are off, but Y Tick Increment also controls the Y grid lines:
$grafico->SetYTickIncrement(100);

# Turn on Y data labels:
$grafico->SetYDataLabelPos('plotin');

# With Y data labels, we don't need Y ticks or their labels, so turn them off.
$grafico->SetYTickLabelPos('none');
$grafico->SetYTickPos('none');

# Format the Y Data Labels as numbers with 1 decimal place.
# Note that this automatically calls SetYLabelType('data').
$grafico->SetPrecisionY(1);

if (isset($_GET['print']) && $_GET['print'] == 'TRUE') {
    $grafico->SetPrintImage(FALSE);
}

$grafico->DrawGraph();

$pdf = new PDF_MemImage();
$pdf->AddPage();
$pdf->GDImage($grafico->img, 30, 20, 240);
$pdf->Output();
        }
