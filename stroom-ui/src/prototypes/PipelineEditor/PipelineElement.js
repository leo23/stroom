/*
 * Copyright 2018 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import React from 'react';
import PropTypes from 'prop-types';

import { compose, withState } from 'recompose';
import { connect } from 'react-redux';

import { DragSource, DropTarget } from 'react-dnd';

import { Image } from 'semantic-ui-react';

import AddElementModal from './AddElementModal';
import { withElement } from './withElement';
import { withPipeline } from './withPipeline';
import { actionCreators } from './redux';
import { canMovePipelineElement } from './pipelineUtils';
import { ItemTypes } from './dragDropTypes';
import { isValidChildType } from './elementUtils'

const { pipelineElementSelected, pipelineElementMoved } = actionCreators;

const withNameNewElementModal = withState('newElementDefinition', 'setNewElementDefinition', undefined);

const dragSource = {
  canDrag(props) {
    return true;
  },
  beginDrag(props) {
    return {
      elementId: props.elementId,
    };
  },
};

function dragCollect(connect, monitor) {
  return {
    connectDragSource: connect.dragSource(),
    isDragging: monitor.isDragging(),
  };
}

const dropTarget = {
  canDrop(props, monitor) {
    const { pipeline, asTree, elementId } = props;
    const thisElement = pipeline.elements.add.filter(element => element.id === elementId)[0];
    const typeOfThisElement = props.elements.elements[thisElement.type]
    switch (monitor.getItemType()) {
      case ItemTypes.ELEMENT:
        let dropeeId = monitor.getItem().elementId;
        let dropee = pipeline.elements.add.filter(element => element.id === dropeeId)[0];
        let dropeeType = props.elements.elements[dropee.type]
        let isValidChild = isValidChildType(typeOfThisElement, dropeeType, 0)

        let isValid = canMovePipelineElement(pipeline, asTree, dropeeId, elementId);

        return isValidChild && isValid;
      case ItemTypes.PALLETE_ELEMENT:
        dropeeType = monitor.getItem().element;
        if(dropeeType){
          let isValidChild = isValidChildType(typeOfThisElement, dropeeType, 0)
          return isValidChild
        }
        else { 
          return true; 
        }
      default:
        return false;
    }
  },
  drop(props, monitor) {
    switch (monitor.getItemType()) {
      case ItemTypes.ELEMENT:
        const newElementId = monitor.getItem().elementId;
        const { elementId, pipelineId, pipelineElementMoved } = props;
        pipelineElementMoved(pipelineId, newElementId, elementId);
        break;
      case ItemTypes.PALLETE_ELEMENT:
        const newElementDefinition = monitor.getItem().element;
        const { setNewElementDefinition } = props;
        setNewElementDefinition(newElementDefinition);
        break;
      default:
        break;
    }
  },
};

function dropCollect(connect, monitor) {
  return {
    connectDropTarget: connect.dropTarget(),
    isOver: monitor.isOver(),
    canDrop: monitor.canDrop(),
    dndIsHappening: (monitor.getItem() !== null)
  };
}

const PipelineElement = ({
  connectDragSource,
  isDragging,
  connectDropTarget,
  isOver,
  canDrop,
  pipelineId,
  elementId,
  element,
  elementDefinition,
  pipelineElementSelected,
  selectedElementId,
  newElementForm,
  newElementDefinition,
  setNewElementDefinition,
  dndIsHappening
}) => {
  let isIconDisabled = false;
  let className = 'Pipeline-element';
  if (isOver) {
    className += ' Pipeline-element__over';
  }
  if (isDragging) {
    className += ' Pipeline-element__dragging ';
  }
  if (isOver) {
    if (canDrop) {
      className += ' Pipeline-element__over_can_drop';
    } else {
      isIconDisabled = true;
      className += ' Pipeline-element__cannot_drop';
    }
  }
  else {
    if(canDrop){
      className += ' Pipeline-element__not_over_can_drop';
    }
    else if (dndIsHappening){
      isIconDisabled = true;
      className += ' Pipeline-element__cannot_drop';
    }
  }

  if(selectedElementId === elementId){
    className += ' Pipeline-element__selected'
  }

  const onClick = () => pipelineElementSelected(pipelineId, elementId);

  return compose(connectDragSource, connectDropTarget)(
    <div className={className} onClick={onClick}>
      <AddElementModal {...{setNewElementDefinition, newElementDefinition, pipelineId, elementId}}  />
      <Image
        className="Pipeline-element__icon"
        alt="X"
        src={require(`./images/${elementDefinition.icon}`)}
        disabled={isIconDisabled}
      />
      <button className='Pipeline-element__type'>
      {elementId}
      </button>
    </div>);
};

PipelineElement.propTypes = {
  // Set by container
  pipelineId: PropTypes.string.isRequired,
  elementId: PropTypes.string.isRequired,

  selectedElementId: PropTypes.string,

  // withPipeline
  pipeline: PropTypes.object.isRequired,
  asTree: PropTypes.object.isRequired,

  // withElement
  element: PropTypes.object.isRequired,

  // Redux actions
  pipelineElementSelected: PropTypes.func.isRequired,
  pipelineElementMoved: PropTypes.func.isRequired,

  // withNameNewElementModal
  newElementDefinition: PropTypes.object,
  setNewElementDefinition: PropTypes.func.isRequired,
};

export default compose(
  connect(
    state => ({
      // state
    }),
    {
      // actions
      pipelineElementSelected,
      pipelineElementMoved
    },
  ),
  withPipeline(),
  withElement(),
  withNameNewElementModal,
  DragSource(ItemTypes.ELEMENT, dragSource, dragCollect),
  DropTarget([ItemTypes.ELEMENT, ItemTypes.PALLETE_ELEMENT], dropTarget, dropCollect),
)(PipelineElement);