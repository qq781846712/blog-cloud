package com.blank.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.blank.system.api.domain.QSysRole;
import com.blank.system.api.domain.SysRole;
import com.blank.system.api.model.dto.RoleDTO;
import com.blank.system.domain.vo.RoleVo;
import com.blank.system.repository.SysRoleRepository;
import com.blank.system.service.ISysRoleService;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色 业务层处理
 */
@RequiredArgsConstructor
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @PersistenceContext
    private EntityManager em;

    private final CriteriaBuilderFactory criteriaBuilderFactory;

    private final SysRoleRepository sysRoleRepository;

    @Override
    public Page<RoleVo> selectList(RoleDTO roleDTO, Pageable pageable) {

        QSysRole qSysRole = QSysRole.sysRole;

        BlazeJPAQuery<Tuple> query = new BlazeJPAQuery<>(em, criteriaBuilderFactory).select(qSysRole.roleId,
                        qSysRole.roleName,
                        qSysRole.roleKey,
                        qSysRole.roleSort,
                        qSysRole.status,
                        qSysRole.remark,
                        qSysRole.dataScope)
                .from(qSysRole)
                .where(
                        roleDTO.getRoleId() != null ? qSysRole.roleId.eq(roleDTO.getRoleId()) : null,
                        StrUtil.isNotBlank(roleDTO.getRoleKey()) ? qSysRole.roleKey.eq(roleDTO.getRoleKey()) : null,
                        StrUtil.isNotBlank(roleDTO.getRoleName()) ? qSysRole.roleName.eq(roleDTO.getRoleName()) : null,
                        StrUtil.isNotBlank(roleDTO.getStatus()) ? qSysRole.status.eq(roleDTO.getStatus()) : null,
                        StrUtil.isNotBlank(roleDTO.getDataScope()) ? qSysRole.dataScope.eq(roleDTO.getDataScope()) : null
                );

        //获取总条数，因query的fetchCount废弃转用 blaze-persistence实现的fetchCount
        long total = query.fetchCount();
        //统计完总数之后设置分页参数
        query.offset(pageable.getOffset()).limit(pageable.getPageSize());

        List<RoleVo> roleList = new ArrayList<>();

        //执行sql并获取结果放入元组
        List<Tuple> fetch = query.fetch();
        //循环元组
        for (Tuple tuple : fetch) {
            //从元组内获取查询结果
            SysRole sysRole = tuple.get(qSysRole);
            roleList.add(BeanUtil.toBean(sysRole, RoleVo.class));
        }

        //对结果进行标准化封装，PageImpl 为spring data实现的，数据格式和其他查询保持一致
        return new PageImpl<RoleVo>(roleList, pageable, total);
    }

}
