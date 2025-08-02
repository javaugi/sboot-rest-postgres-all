def minCostModels(costs, features):
    # Get all unique features
    all_features = set()
    for model in features:
        all_features.update(model)
    unique_features = sorted(all_features)
    n_features = len(unique_features)
    
    # Create feature bitmask for each model
    model_masks = []
    for model_features in features:
        mask = 0
        for feat in model_features:
            mask |= 1 << unique_features.index(feat)
        model_masks.append(mask)
    
    target = (1 << n_features) - 1  # All features covered
    min_cost = float('inf')
    best_combination = []
    
    # Try all possible combinations of models
    n_models = len(costs)
    for mask in range(1, 1 << n_models):
        total_cost = 0
        combined_features = 0
        current_models = []
        
        for i in range(n_models):
            if mask & (1 << i):
                total_cost += costs[i]
                combined_features |= model_masks[i]
                current_models.append(i)
                
                # Early termination if cost exceeds current minimum
                if total_cost >= min_cost:
                    break
        
        if combined_features == target and total_cost < min_cost:
            min_cost = total_cost
            best_combination = current_models
    
    return best_combination
