package com.opple.fa.assetcard.model;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetCardWrapper {
	private GeneralAssetModel generalAssetModel;
	private GeneralAssetModel generalNoCpmputer;
	private AdministrativeAssetModel administrativeModel;
	private ComputerAssetModel computerAssetModel;
	private MachineryEquipmentModel machineryEquipmentModel;
	private MeasuringInstrumentsModel measuringInstrumentsModel;
}
