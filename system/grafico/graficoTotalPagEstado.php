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

$query = "SELECT s.str_name estado, sum(p.db_value) valor, p.int_month mes , p.int_year ano
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

$data = array();

if (isset($resultado)) {
    foreach ($resultado as $r) {
        $data[] = [$r['estado'], $r['valor']];
        $mesano = $r['mes'].'/'.$r['ano'];
    }
} else {
    $data[] = [null, null, null];
}

$grafico = new \PHPlot(1100, 500);
$grafico->SetImageBorderType('plain');

$grafico->SetPlotType('pie');
$grafico->SetDataType('text-data-single');
$grafico->SetDataValues($data);

# Set enough different colors;
$grafico->SetDataColors(
        array(
            '#cc0000', '#ffb3b3', '#ff6600', '#ffd1b3',
            '#803300', '#ffff00', '#808000', '#ff99cc',
            '#cc0066', '#669900', '#bbff33', '#009900',
            '#99ffcc', '#00b3b3', '#ccf2ff', '#53c68c', 
            '#c2c2d6', '#3333cc', '#00ffff', '#003366',
            '#3973ac', '#00001a', '#990000', '#990033',
            '#666633', '#ddddbb','#996633'
            
            )
);

# Main plot title:
$grafico->SetTitle("Total amounts paid per state in: $mesano");

# Build a legend from our data array.
# Each call to SetLegend makes one line as "label: value".
foreach ($data as $row)
    $grafico->SetLegend(utf8_decode(implode(': ', $row)));
# Place the legend in the upper left corner:
$grafico->SetLegendPixels(5, 5);

if (isset($_GET['print']) && $_GET['print'] == 'TRUE') {
    $grafico->SetPrintImage(FALSE);
}

$grafico->DrawGraph();

$pdf = new PDF_MemImage();
$pdf->AddPage();
$pdf->GDImage($grafico->img, 30, 20, 240);
$pdf->Output();

}
