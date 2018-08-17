<?php

ini_set('display_errors', 0);

require_once  "../vendor/autoload.php";
require_once "../dao/reportDAO.php";

session_start();
        if((!isset ($_SESSION['login']) == true) and (!isset ($_SESSION['password']) == true))
        {
            unset($_SESSION['login']);
            unset($_SESSION['password']);
      header('location: ../login.php');
        }else{

$dao = new reportDAO();

$listObjs = $dao->report07();
$dia = $dao ->dateNow();
$hr = $dao ->hourNow();

        $html = "<table border='2' cellspacing='3' cellpadding='5' >";
$html .= "<tr>
            <th>VALOR TOTAL PAGO</th>
            <th>NOME DO ESTADO</th>
        </tr>";
foreach ($listObjs as $var):
    $html.= "<tr>
                <td>$var->valor</td>
                <td>$var->str_name</td>
            </tr>";
endforeach;
$html .= "</table>";


$mpdf = new \Mpdf\Mpdf();
$mpdf->SetCreator('PDF_CREATOR');
$mpdf->SetAuthor('Tassio Sirqueira');
$mpdf->SetTitle('Relatório PDF com o número de beneficiário por estados e o valor total pago por cidade, por mês, ordenados por valor total decrescente');
$mpdf->SetSubject('EconomiC Analyzer');
$mpdf->SetKeywords('TCPDF, ECA');
$mpdf->SetDisplayMode('fullpage');
$mpdf->nbpgPrefix = ' de ';
$mpdf->setFooter("Relatório gerado no dia {$dia} às {$hr} - Página {PAGENO}{nbpg}");
$mpdf->WriteHTML($html);
$mpdf->Output('economicAnalyzer.pdf','I');

exit;
}