package com.netapp.monitoring.model;

import java.util.ArrayList;

public class IdAttributes {

    private String uuid;
    private String name;
    private String node;
    private String junctionPath;
    private String type;
    private String style;
    private String styleExtended;
    private String containingAggregateName;
    private String containingAggregateUuid;
    private String owningVserverName;
    private ArrayList<String> aggrList = new ArrayList<>();

    public IdAttributes() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getJunctionPath() {
        return junctionPath;
    }

    public void setJunctionPath(String junctionPath) {
        this.junctionPath = junctionPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleExtended() {
        return styleExtended;
    }

    public void setStyleExtended(String styleExtended) {
        this.styleExtended = styleExtended;
    }

    public String getContainingAggregateName() {
        return containingAggregateName;
    }

    public void setContainingAggregateName(String containingAggregateName) {
        this.containingAggregateName = containingAggregateName;
    }

    public String getContainingAggregateUuid() {
        return containingAggregateUuid;
    }

    public void setContainingAggregateUuid(String containingAggregateUuid) {
        this.containingAggregateUuid = containingAggregateUuid;
    }

    public ArrayList<String> getAggrList() {
        return aggrList;
    }

    public void setAggrList(ArrayList<String> aggrList) {
        this.aggrList = aggrList;
    }

    public String getOwningVserverName() {
        return owningVserverName;
    }

    public void setOwningVserverName(String owningVserverName) {
        this.owningVserverName = owningVserverName;
    }
}
