<?php

require_once "classes/template.php";
require_once "dao/dashboardDAO.php";

$template = new Template();
$template->header();
$template->sidebar();
$template->mainpanel();

$dao = new dashboardDAO();

$objTotPag = $dao->totalPagamento();
$objPag = $dao->pagUltimoMes();

?>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="content">
                                <div class="row">
                                    <div class="col-xs-5">
                                        <div class="icon-big icon-warning text-center">
                                            <i class="ti-server"></i>
                                        </div>
                                    </div>
                                    <div class="col-xs-7">
                                        <div class="numbers">
                                            <p>Payments</p>
                                            <small><?php echo 'R$' . number_format($objTotPag["soma"],2,",","") ?></small> 
                                        </div>
                                    </div>
                                </div>
                                <div class="footer">
                                    <hr/>
                                    <div class="stats">
                                        <i class="ti-info"></i> Total sum
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="content">
                                <div class="row">
                                    <div class="col-xs-5">
                                        <div class="icon-big icon-success text-center">
                                            <i class="ti-wallet"></i>
                                        </div>
                                    </div>
                                    <div class="col-xs-7">
                                        <div class="numbers">
                                            <p>Payments</p>
                                            <small><?php echo 'R$'. number_format($objPag["soma"],2,",",""); ?></small> 
                                        </div>
                                    </div>
                                </div>
                                <div class="footer">
                                    <hr/>
                                    <div class="stats">
                                        <i class="ti-calendar"></i> Last Month <?php echo $objPag["mes"].' / '. $objPag["ano"];?>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="content">
                                <div class="row">
                                    <div class="col-xs-5">
                                        <div class="icon-big icon-danger text-center">
                                            <i class="ti-pulse"></i>
                                        </div>
                                    </div>
                                    <div class="col-xs-7">
                                        <div class="numbers">
                                            <p>Average</p>
                                            <small><?php echo 'R$'. number_format($objPag["soma"]  /  $objPag["qtde"], 2, ',', ' '); ?></small>
                                        </div>
                                    </div>
                                </div>
                                <div class="footer">
                                    <hr/>
                                    <div class="stats">
                                        <i class="ti-timer"></i> In the last month <?php echo $objPag["mes"] .' / '. $objPag["ano"];?>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="content">
                                <div class="row">
                                    <div class="col-xs-5">
                                        <div class="icon-big icon-info text-center">
                                            <i class="ti-user"></i>
                                        </div>
                                    </div>
                                    <div class="col-xs-7">
                                        <div class="numbers">
                                            <p>Beneficiaries</p>
                                            <small> <?php echo $dao->totalBeneficiarios(); ?></small>
                                        </div>
                                    </div>
                                </div>
                                <div class="footer">
                                    <hr/>
                                    <div class="stats">
                                        <i class="ti-info"></i> Total
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">

                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Monthly beneficiaries</h4>
                                <p class="category">Every year</p>
                            </div>
                            <div class="content">
                                
                                <div id="chartHours" class="ct-chart">
                                    <img style="max-width: 100%; max-height: 100%" src="grafico/graficoTotalBenefMes.php" />
                                </div>
                                <div class="footer">
                                    <div class="chart-legend">
                                        <i class="fa fa-circle text-info"></i> Value
                                        <i class="fa fa-circle text-danger"></i> Value
                                        <i class="fa fa-circle text-warning"></i> Value
                                    </div>
                                    <hr>
                                    <div class="stats">
                                        <i class="ti-info-alt"></i> Historic Serie | <i class="ti-export"></i>
                                        <a href="grafico/graficoTotalBenefMes.php?print=TRUE" target="_blank">Export PDF</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Beneficiaries by state</h4>
                                <p class="category">Monthly update</p>
                            </div>
                            <div class="content">
                                <div id="chartPreferences" class="ct-chart ct-perfect-fourth">
                                    <img style="max-width: 100%; max-height: 100%"  src="grafico/graficoTotalBenefEstado.php" />
                                </div>
                                
                                <div class="footer">
                                    <div class="chart-legend">
                                        <i class="fa fa-circle text-info"></i> Value
                                        <i class="fa fa-circle text-danger"></i> Value
                                        <i class="fa fa-circle text-warning"></i> Value
                                    </div>
                                    <hr>
                                    <div class="stats">
                                        <i class="ti-timer"></i> Total | <i class="ti-export"></i>
                                        <a href="grafico/graficoTotalBenefEstado.php?print=TRUE" target="_blank">Export PDF</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card ">
                            <div class="header">
                                <h4 class="title">Values per state</h4>
                                <p class="category">Monthly update</p>
                            </div>
                            <div class="content">
                                <div id="chartActivity" class="ct-chart">
                                   <img style="max-width: 100%; max-height: 100%"  src="grafico/graficoTotalPagEstado.php" />
                                </div>
                                
                                <div class="footer">
                                    <div class="chart-legend">
                                        <i class="fa fa-circle text-info"></i> Value
                                        <i class="fa fa-circle text-warning"></i> Value
                                    </div>
                                    <hr>
                                    <div class="stats">
                                        <i class="ti-check"></i> Last Month | <i class="ti-export"></i>
                                        <a href="grafico/graficoTotalPagEstado.php?print=TRUE" target="_blank">Export PDF</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <?php

    $template->footer();

    ?>