package cn.xavier.order.query;

import cn.xavier.basic.query.BaseQuery;
import cn.xavier.org.domain.Employee;
import lombok.Data;

@Data
public class PetAcquisitionOrderQuery extends BaseQuery {
    private Long handlerId;
    private Integer state;
}
