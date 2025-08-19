package com.dto.products;

import com.dto.DescribedSceneDto;
import com.dto.SceneDto;

import java.util.List;

public record CRUProductSceneDto(List<SceneDto> valid, List<DescribedSceneDto> invalid) {}
