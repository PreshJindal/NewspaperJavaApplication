module NewsTime {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	exports newsagency;
	exports hawker;
	exports billgen;
	exports billcollector;
	exports customerpan;
	exports hawkertable;
	exports billtable;
	exports customerviewer;
	exports dashboard;
	
	opens dashboard to javafx.graphics,javafx.fxml;
	opens customerviewer to javafx.graphics,javafx.fxml;
	opens billtable to javafx.graphics,javafx.fxml;
	opens hawkertable to javafx.graphics,javafx.fxml;
	opens billcollector to javafx.graphics,javafx.fxml;
	opens customerpan to javafx.graphics,javafx.fxml;
	opens billgen to javafx.graphics,javafx.fxml;
	opens hawker to javafx.graphics,javafx.fxml;
	opens newsagency to javafx.graphics,javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
}
