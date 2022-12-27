package com.mengship.service.impl;

import com.mengship.entity.Ship;
import com.mengship.mapper.ShipMapper;
import com.mengship.service.IShipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author meng
 * @since 2022-12-27
 */
@Service
public class ShipServiceImpl extends ServiceImpl<ShipMapper, Ship> implements IShipService {

}
